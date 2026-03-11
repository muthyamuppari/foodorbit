package com.alpha.foodorbit.controller;

import com.alpha.foodorbit.dto.RestaurantReqDto;
import com.alpha.foodorbit.entities.Item;
import com.alpha.foodorbit.entities.Order;
import com.alpha.foodorbit.entities.Restaurant;
import com.alpha.foodorbit.service.RedisService;
import com.alpha.foodorbit.service.RestaurantService;
import com.alpha.foodorbit.special.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantRegController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/restaurant/register")
    private void adding(@RequestBody RestaurantReqDto restaurantReqDto){

        restaurantService.adding(restaurantReqDto);
    }

    @GetMapping("/find/restaurant")
    public ResponseEntity<ResponseStructure<Restaurant>> findRestaurant(@RequestParam long mobno){
//        Restaurant r=restaurantService.findRestaurant(mobno);
        return restaurantService.findRestaurant(mobno);


    }

    @DeleteMapping("/delete/restaurant")
    public void deleteRestaurant(@RequestParam long mobno){
        restaurantService.deleteRestaurant(mobno);
    }


    @PostMapping("/restaurant/additemtomenu")
    public ResponseEntity<Restaurant> addtomenu(@RequestBody Item item, @RequestParam long mobno){
        Restaurant restaurant=restaurantService.addtomenu(item,mobno);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
            }

    @PatchMapping("/restaurant/updateStatus")
    public void updateStatus(@RequestParam long mobno){
        restaurantService.updateStatus(mobno);

    }

    @PatchMapping("/restaurant/updateItemAvailability")
    public void updateItemAvailability(@RequestParam long mobno,@RequestParam int Itemid){
        restaurantService.updateItemAvailability(mobno,Itemid);
    }

      @Autowired
    private RedisService redisService;

    @GetMapping("/findnearbydeliverypartners")
    public List<String> findNearbyPartners(@RequestParam double latitude,@RequestParam double longitude,double radiusKm){
        return  redisService.findNearbyPartners(latitude,longitude,radiusKm);
    }

    @PostMapping("/restaurant/acceptorder")
    public List<String> acceptorder(@RequestParam double latitude,@RequestParam double longitude,@RequestParam Integer orderid){
        return  restaurantService.acceptorder(latitude,longitude,orderid);

    }
    @DeleteMapping("/restaurant/removeItemFromMenu")
    public ResponseEntity<ResponseStructure<String>> removeItemFromMenu(@RequestParam long mobno,@RequestParam Integer itemid){
        return restaurantService.removeItemFromMenu(mobno,itemid);
    }

    @GetMapping("/restaurant/getMenu")
    public List<Item> getMenu(@RequestParam long mobno){
        return restaurantService.getMenu(mobno);
    }
    @PutMapping("/restaurant/updateItemDetails")
    public String updateItemDetails(@RequestParam long mobno,@RequestParam Integer itemid,Item updateItem){
        return restaurantService.updateItemDetails(mobno,itemid,updateItem);
    }

    @PostMapping("/restaurant/cancelOrder")
    public ResponseEntity<ResponseStructure<String>> orderCancelled(@RequestParam long mobno, @RequestParam Integer orderid){
         return  restaurantService.orderCancelled(mobno,orderid);
    }






}
