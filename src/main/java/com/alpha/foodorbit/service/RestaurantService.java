package com.alpha.foodorbit.service;

import com.alpha.foodorbit.dto.RestaurantReqDto;
import com.alpha.foodorbit.entities.Address;
import com.alpha.foodorbit.entities.Item;
import com.alpha.foodorbit.entities.Restaurant;
import com.alpha.foodorbit.repository.AddressRepository;
import com.alpha.foodorbit.repository.ItemRepository;
import com.alpha.foodorbit.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
     private RestTemplate restTemplate;
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

    public Restaurant findRestaurant(long mobno) {
        return restaurantRepository.findByMobno(mobno).orElseThrow(()->new RuntimeException("Restaurant Not Found"));
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
}
