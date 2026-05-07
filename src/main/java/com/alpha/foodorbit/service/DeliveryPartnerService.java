package com.alpha.foodorbit.service;

import com.alpha.foodorbit.dto.DeliveryPartnerDto;
import com.alpha.foodorbit.dto.RestaurantReqDto;
import com.alpha.foodorbit.entities.Address;
import com.alpha.foodorbit.entities.DeliveryPartner;
import com.alpha.foodorbit.entities.Order;
import com.alpha.foodorbit.exception.DeliveryPartnerNotFound;
import com.alpha.foodorbit.exception.InvalidOtpException;
import com.alpha.foodorbit.exception.OrderNotFoundException;
import com.alpha.foodorbit.repository.AddressRepository;
import com.alpha.foodorbit.repository.DeliveryPartnerRepository;
import com.alpha.foodorbit.repository.OrderRepository;
import com.alpha.foodorbit.special.ResponseStructure;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.print.DocFlavor;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DeliveryPartnerService {

    @Autowired
    private DeliveryPartnerRepository deliveryPartnerRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AddressRepository addressRepository;

@Autowired
private EmailService emailService;
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

        DeliveryPartner savedPartner = deliveryPartnerRepository.save(deliveryPartner);

        emailService.sendRegistrationEmail(savedPartner.getEmail(),savedPartner.getName());

    }


    public void deletePartner(long mobno) {
        DeliveryPartner d = deliveryPartnerRepository.findByMobno(mobno).orElseThrow(()->new DeliveryPartnerNotFound("Delivery Partner Not Found"));
        deliveryPartnerRepository.delete(d);

    }

    public DeliveryPartner findDeliveryPartner(long mobno) {

        return deliveryPartnerRepository.findByMobno(mobno).orElseThrow(()->new DeliveryPartnerNotFound("Delivery Partner Not Found"));
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
           order.setStatus("DeliveryPartnerAssigned-OrderPreparing");
           orderRepository.save(order);
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

    public ResponseEntity<ResponseStructure<String>> markOrderAsDelivered(long dpmobno, Integer orderId, int otp) {
        DeliveryPartner deliveryPartner = deliveryPartnerRepository.findByMobno(dpmobno).orElseThrow(() -> new DeliveryPartnerNotFound("Delivery Partner Not Found"));
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order not found with thid id"));
        if(order.getOtp()==otp){
            order.setStatus("Order_Delivered");
            double deliveryCharges = order.getDelivery_charges();
            double amount= order.getTotalCost()-deliveryCharges;
            double RestShare= (amount*85)/100;
            double deliveryPartnerShare=((amount*10)/100) + deliveryCharges;
            order.getRestaurant().setWallet(order.getRestaurant().getWallet()+RestShare);
            order.getDeliveryPartner().setWallet(order.getDeliveryPartner().getWallet()+ deliveryPartnerShare);
        }else{
            throw new InvalidOtpException("Invalid OTP");
        }

     orderRepository.save(order);
        ResponseStructure<String> rs=new ResponseStructure<>();
        rs.setData("Delivered");
        rs.setMessage("Order Delivered Successfully");
        rs.setStatuscode(200);
        return new ResponseEntity<>(rs, HttpStatus.OK);

    }
}
