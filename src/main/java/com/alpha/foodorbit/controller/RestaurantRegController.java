package com.alpha.foodorbit.controller;

import com.alpha.foodorbit.dto.RestaurantReqDto;
import com.alpha.foodorbit.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantRegController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/restaurant/register")
    private void adding(@RequestBody RestaurantReqDto restaurantReqDto){

        restaurantService.adding(restaurantReqDto);
    }
}
