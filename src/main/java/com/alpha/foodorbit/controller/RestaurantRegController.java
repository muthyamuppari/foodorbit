package com.alpha.foodorbit.controller;

import com.alpha.foodorbit.dto.RestaurantReqDto;
import com.alpha.foodorbit.entities.Item;
import com.alpha.foodorbit.entities.Restaurant;
import com.alpha.foodorbit.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestaurantRegController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/restaurant/register")
    private void adding(@RequestBody RestaurantReqDto restaurantReqDto){

        restaurantService.adding(restaurantReqDto);
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



}
