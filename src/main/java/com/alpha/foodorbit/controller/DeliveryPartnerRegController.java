package com.alpha.foodorbit.controller;

import com.alpha.foodorbit.dto.DeliveryPartnerDto;
import com.alpha.foodorbit.dto.RestaurantReqDto;
import com.alpha.foodorbit.entities.DeliveryPartner;
import com.alpha.foodorbit.service.DeliveryPartnerService;
import com.alpha.foodorbit.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DeliveryPartnerRegController {

    @Autowired
    private DeliveryPartnerService deliveryPartnerService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @PostMapping("/deliveryPartner/register")
    public void adding(@RequestBody DeliveryPartnerDto deliveryPartnerDto) {
        deliveryPartnerService.adding(deliveryPartnerDto);
    }

    @DeleteMapping("/delete/deliveryPartner")
    public void deletePartner(@RequestParam long mobno) {
        deliveryPartnerService.deletePartner(mobno);

    }

    @GetMapping("/find/deliveryPartner")
    public ResponseEntity<DeliveryPartner> findDeliveryPartner(@RequestParam long mobno) {
        DeliveryPartner d = deliveryPartnerService.findDeliveryPartner(mobno);
        return new ResponseEntity<>(d, HttpStatus.OK);
    }


    @PostMapping("/deliveryPartner/updateDpLoc")
    public ResponseEntity<String> updateDpLoc(@RequestParam Integer partnerid, @RequestParam double latitude, double longitude) {
        String s = redisService.updateDpLoc(partnerid, latitude, longitude);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @PostMapping("/deliveryPartner/acceptorder")
    public String acceptorder(@RequestParam Integer orderid, @RequestParam Integer partnerid) {
        boolean accepted = deliveryPartnerService.acceptorder(orderid, partnerid);

        return accepted ? "Order Assigned Successfully" : "Order Already Taken";
    }

}
