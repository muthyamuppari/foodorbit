package com.alpha.foodorbit.controller;

import com.alpha.foodorbit.entities.DeliveryPartner;
import com.alpha.foodorbit.service.DeliveryPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteDeliveryPartner {

    @Autowired
    private DeliveryPartnerService deliveryPartnerService;

    @DeleteMapping("/delete/deliveryPartner")
    public void deletePartner(@RequestParam long mobno){
        deliveryPartnerService.deletePartner(mobno);

    }

}
