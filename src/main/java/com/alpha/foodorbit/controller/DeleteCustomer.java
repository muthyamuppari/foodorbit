package com.alpha.foodorbit.controller;

import com.alpha.foodorbit.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteCustomer {

    @Autowired
    private CustomerService customerService;

    @DeleteMapping("/delete/customer")
    public void deleteCustomer(@RequestParam long mobno){
        customerService.deleteCustomer(mobno);

    }
}
