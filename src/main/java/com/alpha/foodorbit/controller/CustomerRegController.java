package com.alpha.foodorbit.controller;

import com.alpha.foodorbit.dto.CustomerReqDto;
import com.alpha.foodorbit.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerRegController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customer/register")
    public void customerdto(@RequestBody CustomerReqDto customerReqDto){
       customerService.adding(customerReqDto);
    }
}
