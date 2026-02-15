package com.alpha.foodorbit.controller;

import com.alpha.foodorbit.entities.DeliveryPartner;
import com.alpha.foodorbit.service.DeliveryPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FindDeliveryPartner {

    @Autowired
    private DeliveryPartnerService deliveryPartnerService;
    @GetMapping("/find/deliveryPartner")
    public ResponseEntity<DeliveryPartner> findDeliveryPartner(@RequestParam long mobno){
            DeliveryPartner d=  deliveryPartnerService.findDeliveryPartner(mobno);
            return new ResponseEntity<>(d, HttpStatus.OK);
    }

}
