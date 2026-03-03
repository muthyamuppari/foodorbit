package com.alpha.foodorbit.controller;

import com.alpha.foodorbit.dto.CustomerReqDto;
import com.alpha.foodorbit.entities.CartItem;
import com.alpha.foodorbit.entities.Customer;
import com.alpha.foodorbit.entities.Order;
import com.alpha.foodorbit.entities.Restaurant;
import com.alpha.foodorbit.service.CustomerService;
import com.alpha.foodorbit.service.RestaurantService;
import com.alpha.foodorbit.special.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerRegController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RestaurantService restaurantService;

//    @PostMapping("/customer/register")
//    public void customerdto(@RequestBody CustomerReqDto customerReqDto){
//       customerService.adding(customerReqDto);
//    }
//}

    @PostMapping("/customer/register")
    public Customer createCustomer(@RequestBody CustomerReqDto customerReqDto) {
        return customerService.saveCustomer(customerReqDto);
    }

    @DeleteMapping("/delete/customer")
    public void deleteCustomer(@RequestParam long mobno) {
        customerService.deleteCustomer(mobno);

    }

    @GetMapping("/find/customer")
    public ResponseEntity<Customer> findCustomer(@RequestParam long mobno) {
        Customer c = customerService.findCustomer(mobno);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @PostMapping("/customer/SearchItemOrRestaurant")
    public ResponseEntity<List<Restaurant>> SearchItemOrRestaurant(@RequestParam long mobno, @RequestParam String SearchKey) {
        List<Restaurant> result = restaurantService.searchItemorRestaurant(mobno, SearchKey);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/customer/addtocart")
    public ResponseEntity<String> addtocart(@RequestParam long mobno, @RequestParam int Itemid, @RequestParam int quantity) {
        customerService.addtocart(mobno, Itemid, quantity);
        return new ResponseEntity<>("Added To Cart", HttpStatus.OK);

    }

    @PostMapping("/customer/addtocartt")
    public ResponseEntity<CartItem> addtocartt(@RequestParam long mobno, @RequestParam int Itemid, @RequestParam int quantity) {
        CartItem addtocartt = customerService.addtocartt(mobno, Itemid, quantity);
        return new ResponseEntity<>(addtocartt, HttpStatus.OK);
    }

    @GetMapping("/customer/getCart")
    public ResponseEntity<List<CartItem>> getAllCart(@RequestParam long mobno) {
        List<CartItem> allCart = customerService.getAllCart(mobno);
        return new ResponseEntity<>(allCart, HttpStatus.OK);

    }

    @PostMapping("/customer/placeOrder")
    public ResponseEntity<ResponseStructure<Order>> placeOrder(@RequestParam long mobno, @RequestParam String PaymentType, @RequestParam String AddressType
            , @RequestParam String SpecialRequest) {
        return customerService.placingOrder(mobno, PaymentType, AddressType, SpecialRequest);
    }

    @PostMapping("/customer/ConfirmPlacingOrder")
    public ResponseEntity<String> confirmPlacingOrder(@RequestParam int orderid) {
        customerService.confirmPlacingOrder(orderid);
        return new ResponseEntity<>("Order Placed Successfully", HttpStatus.OK);
    }

    @PostMapping("/customer/denyPlacingOrder")
    public ResponseEntity<String> denyPlacingOrder(@RequestParam int orderid) {
        customerService.denyPlacingOrder(orderid);
        return new ResponseEntity<>("Order Cancelled Successfully", HttpStatus.OK);
    }

}

