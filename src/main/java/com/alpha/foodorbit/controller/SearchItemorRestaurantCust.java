package com.alpha.foodorbit.controller;

import com.alpha.foodorbit.entities.Restaurant;
import com.alpha.foodorbit.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchItemorRestaurantCust {

    @Autowired
    private RestaurantService restaurantService;

        @PostMapping("/customer/SearchItemOrRestaurant")
    public ResponseEntity<List<Restaurant>> SearchItemOrRestaurant(@RequestParam long mobno, @RequestParam String SearchKey){
        List<Restaurant> result = restaurantService.searchItemorRestaurant(mobno, SearchKey);
        return  new ResponseEntity<>(result, HttpStatus.OK);
    }

}
