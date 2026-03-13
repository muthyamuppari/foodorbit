package com.alpha.foodorbit.service;

import com.alpha.foodorbit.dto.RestaurantReqDto;
import com.alpha.foodorbit.entities.*;
import com.alpha.foodorbit.exception.ItemNotFoundException;
import com.alpha.foodorbit.exception.RestaurantBlockedException;
import com.alpha.foodorbit.exception.RestaurantNotFound;
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

        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantReqDto.getName());
        restaurant.setMobno(restaurantReqDto.getMobno());
        restaurant.setMailid(restaurantReqDto.getMailid());
        restaurant.setDescription(restaurantReqDto.getDescription());
        restaurant.setPackagingFees(restaurantReqDto.getPackagingFees());
        restaurant.setType(restaurantReqDto.getType());
        restaurant.setStatus("closed");

        Address address = new Address();


        Map response = restTemplate.getForObject("https://us1.locationiq.com/v1/reverse?key=pk.5038d98b114a8653a2d8716f69a70c50"
                + "&lat=" + restaurantReqDto.getLocationCordinate().getLatitude() +
                "&lon=" + restaurantReqDto.getLocationCordinate().getLongitute() + "&format=json", Map.class
        );
        Map add = (Map) response.get("address");
        address.setPincode((String) add.get("postcode"));
        address.setCity((String) add.get("city"));
        address.setCountry((String) add.get("country"));
        address.setState((String) add.get("state"));


        restaurant.setAddress(address);
        addressRepository.save(address);
        restaurantRepository.save(restaurant);


    }

    public void deleteRestaurant(long mobno) {
        Restaurant r = restaurantRepository.findByMobno(mobno).orElseThrow(() -> new RuntimeException("RESTAURANT NOT FOUND"));
        restaurantRepository.delete(r);
    }

    public ResponseEntity<ResponseStructure<Restaurant>> findRestaurant(long mobno) {

        Restaurant restaurant = restaurantRepository.findByMobno(mobno).orElseThrow(() -> new RuntimeException("Restaurant Not Found"));
        ResponseStructure<Restaurant> rs = new ResponseStructure<>();
        rs.setStatuscode(HttpStatus.FOUND.value());
        rs.setMessage("Restaurant Fetched Successfully");
        rs.setData(restaurant);
        return new ResponseEntity<ResponseStructure<Restaurant>>(rs, HttpStatus.FOUND);
    }

    public Restaurant addtomenu(Item item, long mobno) {
        Restaurant restaurant = restaurantRepository.findByMobno(mobno).orElseThrow(() -> new RuntimeException("Restaurant not found"));
        restaurant.getMenu().add(item);
        item.setRestaurant(restaurant);
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    public void updateStatus(long mobno) {
        Restaurant restaurant = restaurantRepository.findByMobno(mobno).orElseThrow(() -> new RuntimeException("restaurant not found"));
        if (restaurant.getStatus().equals("closed")) restaurant.setStatus("open");
        else if (restaurant.getStatus().equals("open")) restaurant.setStatus("closed");
        restaurantRepository.save(restaurant);

    }

    public void updateItemAvailability(long mobno, int itemid) {
        Restaurant restaurant = restaurantRepository.findByMobno(mobno).orElseThrow(() -> new RuntimeException("Restaurant not found"));
        Item item = itemRepository.findById(itemid).orElseThrow(() -> new RuntimeException("Item not found"));

        if (item.getAvailability().equals("Available")) item.setAvailability("Not Available");
        else if (item.getAvailability().equals("Not Available")) item.setAvailability("Available");

        itemRepository.save(item);
    }


    public List<Restaurant> searchItemorRestaurant(long mobno, String searchKey) {
        Customer cust = customerRepository.findByMobno(mobno).orElseThrow(() -> new RuntimeException("Customer not found"));
        String city = cust.getAddress().get(0).getCity();
        List<Restaurant> restaurants = restaurantRepository.findByAddress_City(city);
        return restaurants.stream().filter(r -> r.getMenu().stream()
                .anyMatch(menu -> menu.getName().toLowerCase().contains(searchKey.toLowerCase())) ||
                r.getName().toLowerCase().contains(searchKey.toLowerCase())).toList();


    }

    @Autowired
    private RedisService redisService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public List<String> acceptorder(double latitude, double longitude, Integer orderid) {
        Order order = orderRepository.findById(orderid).orElseThrow(() -> new RuntimeException("Order does not exist"));
        List<String> nearbyPartners = redisService.findNearbyPartners(latitude, longitude, 5.0);
        String orderKey = "order:" + orderid;
        for (String partnerid : nearbyPartners) {
            Long size = redisTemplate.opsForSet().add(orderKey, partnerid);

        }
        order.setStatus("OrderPlaced");
        orderRepository.save(order);
        return nearbyPartners;
    }


    public ResponseEntity<ResponseStructure<String>> removeItemFromMenu(long mobno, Integer itemid) {
        Restaurant restaurant = restaurantRepository.findByMobno(mobno).orElseThrow(() -> new RestaurantNotFound("Restaurant not found"));

        Item item = itemRepository.findById(itemid).orElseThrow(() -> new ItemNotFoundException("Item not found"));
        restaurant.getMenu().remove(item);
        itemRepository.delete(item);
        ResponseStructure<String> rs = new ResponseStructure<>();
        rs.setData("Success");
        rs.setMessage("Item Removed Successfully from menu");
        rs.setStatuscode(200);
        return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);


    }

    public List<Item> getMenu(long mobno) {
        Restaurant restaurant = restaurantRepository.findByMobno(mobno).orElseThrow(() -> new RestaurantNotFound("Restaurant not found"));
        return restaurant.getMenu();
    }

    public String updateItemDetails(long mobno, Integer itemid, Item updateItem) {
        Restaurant restaurant = restaurantRepository.findByMobno(mobno).orElseThrow(() -> new RestaurantNotFound("Restaurant not found"));

        Item item = itemRepository.findById(itemid).orElseThrow(() -> new ItemNotFoundException("Item not found"));
        item.setName(updateItem.getName());
        item.setPrice(updateItem.getPrice());
        item.setDescription(updateItem.getDescription());
        itemRepository.save(item);
        return "Item Updated Succesfully";
    }

    public ResponseEntity<ResponseStructure<String>> orderCancelled(long mobno, Integer orderid) {
        Restaurant restaurant = restaurantRepository.findByMobno(mobno).orElseThrow(() -> new RestaurantNotFound("Restaurant not found"));
        Order order = orderRepository.findById(orderid).orElseThrow(() -> new RuntimeException("Order does not exist"));

        if("Blocked".equalsIgnoreCase(restaurant.getStatus())){
            throw new RestaurantBlockedException("Restaurant is Blocked,Pay Penalty to accept Order.");
        }
        order.setStatus("CancelledByRestaurant");
        order.setRestaurant(restaurant);
        double penalty=0;
        penalty= (order.getTotalCost() * 10)/100;
        restaurant.setWallet(restaurant.getWallet()-penalty);
        if(restaurant.getWallet()<=-1000){
            restaurant.setStatus("Blocked");
        }

        System.out.println(restaurant.getPenalty());

        restaurantRepository.save(restaurant);
        orderRepository.save(order);
       ResponseStructure<String> rs =new ResponseStructure<>();
       rs.setData("Cancelling Initiated");
       rs.setMessage("OrderCancelledByRest");
       rs.setStatuscode(200);
       return new ResponseEntity<>(rs,HttpStatus.OK);

    }
    }
