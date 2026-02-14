package com.alpha.foodorbit.controller;

import com.alpha.foodorbit.dto.DeliveryPartnerDto;
import com.alpha.foodorbit.dto.RestaurantReqDto;
import com.alpha.foodorbit.entities.DeliveryPartner;
import com.alpha.foodorbit.service.DeliveryPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeliveryPartnerController {

    @Autowired
    private DeliveryPartnerService deliveryPartnerService;

    @PostMapping("/deliveryPartner/register")
    public void adding(@RequestBody DeliveryPartnerDto deliveryPartnerDto){
        deliveryPartnerService.adding(deliveryPartnerDto);
    }
}
