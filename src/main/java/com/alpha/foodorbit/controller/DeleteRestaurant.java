package com.alpha.foodorbit.controller;

import com.alpha.foodorbit.entities.Restaurant;
import com.alpha.foodorbit.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteRestaurant {

    @Autowired
    private RestaurantService restaurantService;

    @DeleteMapping("/delete/restaurant")
    public void deleteRestaurant(@RequestParam long mobno){
        restaurantService.deleteRestaurant(mobno);
    }


}
