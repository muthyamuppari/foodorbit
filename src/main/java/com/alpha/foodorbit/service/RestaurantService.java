package com.alpha.foodorbit.service;

import com.alpha.foodorbit.dto.RestaurantReqDto;
import com.alpha.foodorbit.entities.*;
import com.alpha.foodorbit.repository.*;
import com.alpha.foodorbit.special.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CustomerRepository customerRepository;

     @Autowired
     private RestTemplate restTemplate;

     @Autowired
     private OrderRepository orderRepository;
    public void adding(RestaurantReqDto restaurantReqDto) {

        Restaurant restaurant=new Restaurant();
        restaurant.setName(restaurantReqDto.getName());
        restaurant.setMobno(restaurantReqDto.getMobno());
        restaurant.setMailid(restaurantReqDto.getMailid());
        restaurant.setDescription(restaurantReqDto.getDescription());
        restaurant.setPackagingFees(restaurantReqDto.getPackagingFees());
        restaurant.setType(restaurantReqDto.getType());
        restaurant.setStatus("closed");

        Address address=new Address();


        Map response=restTemplate.getForObject("https://us1.locationiq.com/v1/reverse?key=pk.5038d98b114a8653a2d8716f69a70c50"
                + "&lat="+restaurantReqDto.getLocationCordinate().getLatitude() +
                "&lon="+restaurantReqDto.getLocationCordinate().getLongitute()+ "&format=json", Map.class
       );
            Map add=(Map) response.get("address");
            address.setPincode((String) add.get("postcode"));
            address.setCity((String) add.get("city"));
            address.setCountry((String) add.get("country"));
            address.setState((String) add.get("state"));



            restaurant.setAddress(address);
         addressRepository.save(address);
        restaurantRepository.save(restaurant);





    }

    public void deleteRestaurant(long mobno) {
          Restaurant r=  restaurantRepository.findByMobno(mobno).orElseThrow(()->new RuntimeException("RESTAURANT NOT FOUND"));
          restaurantRepository.delete(r);
    }

    public ResponseEntity<ResponseStructure<Restaurant>> findRestaurant(long mobno) {

        Restaurant restaurant = restaurantRepository.findByMobno(mobno).orElseThrow(() -> new RuntimeException("Restaurant Not Found"));
        ResponseStructure<Restaurant> rs=new ResponseStructure<>();
        rs.setStatuscode(HttpStatus.FOUND.value());
        rs.setMessage("Restaurant Fetched Successfully");
        rs.setData(restaurant);
        return new ResponseEntity<ResponseStructure<Restaurant>>(rs,HttpStatus.FOUND);
    }

    public Restaurant addtomenu(Item item, long mobno) {
        Restaurant restaurant= restaurantRepository.findByMobno(mobno).orElseThrow(()->new RuntimeException("Restaurant not found"));
        restaurant.getMenu().add(item);
        item.setRestaurant(restaurant);
        restaurantRepository.save(restaurant);
        return  restaurant;
    }

    public void updateStatus(long mobno) {
        Restaurant restaurant=restaurantRepository.findByMobno(mobno).orElseThrow(()->new RuntimeException("restaurant not found"));
        if(restaurant.getStatus().equals("closed"))restaurant.setStatus("open");
        else if(restaurant.getStatus().equals("open"))restaurant.setStatus("closed");
        restaurantRepository.save(restaurant);

    }

    public void updateItemAvailability(long mobno, int itemid) {
         Restaurant restaurant=restaurantRepository.findByMobno(mobno).orElseThrow(()->new RuntimeException("Restaurant not found"));
         Item item=itemRepository.findById(itemid).orElseThrow(()->new RuntimeException("Item not found"));

         if(item.getAvailability().equals("Available"))item.setAvailability("Not Available");
         else if(item.getAvailability().equals("Not Available")) item.setAvailability("Available");

         itemRepository.save(item);
    }



    public List<Restaurant> searchItemorRestaurant(long mobno, String searchKey) {
        Customer cust=customerRepository.findByMobno(mobno).orElseThrow(()->new RuntimeException("Customer not found"));
         String city=cust.getAddress().get(0).getCity();
         List<Restaurant> restaurants=restaurantRepository.findByAddress_City(city);
         return restaurants.stream().filter(r->r.getMenu().stream()
                 .anyMatch(menu->menu.getName().toLowerCase().contains(searchKey.toLowerCase())) ||
                 r.getName().toLowerCase().contains(searchKey.toLowerCase())).toList();


    }
    @Autowired
    private RedisService redisService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public List<String> acceptorder(double latitude, double longitude, Integer orderid) {
           Order order= orderRepository.findById(orderid).orElseThrow(()->new RuntimeException("Order does not exist"));
         List<String> nearbyPartners= redisService.findNearbyPartners(latitude,longitude,5.0);
         String orderKey= "order:"+orderid;
         for(String partnerid:nearbyPartners){
             Long size = redisTemplate.opsForSet().add(orderKey, partnerid);
//             redisTemplate.opsForHash().put(orderKey,partnerid,String.valueOf(order.getCost()));
         }
          return nearbyPartners;
    }


}
