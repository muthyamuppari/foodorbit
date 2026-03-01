package com.alpha.foodorbit.controller;

import com.alpha.foodorbit.entities.Customer;
import com.alpha.foodorbit.entities.Payment;
import com.alpha.foodorbit.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlaceOrder {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customer/placeOrder")
    public ResponseEntity<String> placeOrder(@RequestParam long mobno, @RequestParam String PaymentType, @RequestParam String AddressType
            , @RequestParam String SpecialRequest){
        customerService.placingOrder(mobno, PaymentType,AddressType,SpecialRequest);
        return new ResponseEntity<>("Order Placed Successfully", HttpStatus.OK);
    }


}
