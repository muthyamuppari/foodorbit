package com.alpha.foodorbit.controller;

import com.alpha.foodorbit.entities.CartItem;
import com.alpha.foodorbit.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class GetCart {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer/getCart")
    public ResponseEntity<List<CartItem>> getAllCart(@RequestParam long mobno){
        List<CartItem> allCart = customerService.getAllCart(mobno);
        return  new ResponseEntity<>(allCart, HttpStatus.OK);

    }
}
