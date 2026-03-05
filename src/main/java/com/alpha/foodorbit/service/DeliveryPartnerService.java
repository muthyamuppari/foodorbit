package com.alpha.foodorbit.service;

import com.alpha.foodorbit.dto.DeliveryPartnerDto;
import com.alpha.foodorbit.dto.RestaurantReqDto;
import com.alpha.foodorbit.entities.Address;
import com.alpha.foodorbit.entities.DeliveryPartner;
import com.alpha.foodorbit.entities.Order;
import com.alpha.foodorbit.repository.AddressRepository;
import com.alpha.foodorbit.repository.DeliveryPartnerRepository;
import com.alpha.foodorbit.repository.OrderRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.print.DocFlavor;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class DeliveryPartnerService {

    @Autowired
    private DeliveryPartnerRepository deliveryPartnerRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AddressRepository addressRepository;


    public void adding(DeliveryPartnerDto deliveryPartnerDto) {
        DeliveryPartner deliveryPartner = new DeliveryPartner();
        deliveryPartner.setName(deliveryPartnerDto.getName());
        deliveryPartner.setMobno(deliveryPartnerDto.getMobno());
        deliveryPartner.setEmail(deliveryPartnerDto.getEmail());
        deliveryPartner.setVehicleNo(deliveryPartnerDto.getVehicleNo());

        Address address = new Address();

        Map response = restTemplate.getForObject("https://us1.locationiq.com/v1/reverse?key=pk.5038d98b114a8653a2d8716f69a70c50"
                + "&lat=" + deliveryPartnerDto.getLocationCordinate().getLatitude() +
                "&lon=" + deliveryPartnerDto.getLocationCordinate().getLongitute() + "&format=json", Map.class
        );
        Map add = (Map) response.get("address");
        address.setPincode((String) add.get("postcode"));
        address.setCity((String) add.get("city"));
        address.setCountry((String) add.get("country"));
        address.setState((String) add.get("state"));
        address.setLatitude(deliveryPartnerDto.getLocationCordinate().getLatitude());
        address.setLongitude(deliveryPartnerDto.getLocationCordinate().getLongitute());
        deliveryPartner.setAddress(address);
        addressRepository.save(address);

        deliveryPartnerRepository.save(deliveryPartner);
    }

    public void deletePartner(long mobno) {
        DeliveryPartner d = deliveryPartnerRepository.findByMobno(mobno);
        deliveryPartnerRepository.delete(d);

    }

    public DeliveryPartner findDeliveryPartner(long mobno) {

        return deliveryPartnerRepository.findByMobno(mobno);
    }

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private OrderRepository orderRepository;

    public boolean acceptorder(Integer orderid, Integer partnerid) {
        Order order = orderRepository.findById(orderid).orElseThrow(() -> new RuntimeException("Order not found"));
        DeliveryPartner deliveryPartner = deliveryPartnerRepository.findById(partnerid).orElseThrow(()
                -> new RuntimeException("partner not found"));


        String lockKey = "order_lock" + orderid;
        Boolean locked = redisTemplate.opsForValue().setIfAbsent(lockKey, partnerid.toString());
        if (Boolean.TRUE.equals(locked)) {
            order.setDeliveryPartner(deliveryPartner);
            orderRepository.save(order);
            redisTemplate.delete("order:" + orderid);

            return true;
        }
        return false;
    }

    public void getDirectionToRest(Integer partnerId, double restlat,
                                   double restlong, HttpServletResponse resp) throws IOException {

        String key="deliverypartner:location";
        List<Point> points = redisTemplate.opsForGeo().position(key, partnerId.toString());

        if(points== null || points.isEmpty()){
            throw new RuntimeException("Delivery Partner Location not found");
        }
              Point p =points.get(0);
        double  dplon= p.getX();
       double dplat= p.getY();


        String getdir="https://www.google.com/maps/dir/?api=1&origin="+dplat+","+dplon+"&destination="+restlat+
                ","+restlong+"&travelmode=driving";
        resp.sendRedirect(getdir);

    }


    public void getDirectionToCust(double restlat, double restlon, double custlat, double custlong, HttpServletResponse rest) throws IOException {

        String getdir="https://www.google.com/maps/dir/?api=1&origin="+restlat+","+restlon+"&destination="+custlat+
                ","+custlong+"&travelmode=driving";
        rest.sendRedirect(getdir);
    }
}
