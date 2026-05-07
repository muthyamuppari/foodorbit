package com.alpha.foodorbit.controller;

import com.alpha.foodorbit.dto.CartResponseDto;
import com.alpha.foodorbit.dto.CustomerReqDto;
import com.alpha.foodorbit.dto.OrderNeedConsentDto;
import com.alpha.foodorbit.entities.CartItem;
import com.alpha.foodorbit.entities.Customer;
import com.alpha.foodorbit.entities.Order;
import com.alpha.foodorbit.entities.Restaurant;
import com.alpha.foodorbit.service.CustomerService;
import com.alpha.foodorbit.service.RestaurantService;
import com.alpha.foodorbit.special.ResponseStructure;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
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
    public Customer createCustomer(@RequestBody @Valid CustomerReqDto customerReqDto) {
        return customerService.saveCustomer(customerReqDto);
    }

    @DeleteMapping("/delete/customer")
    public void deleteCustomer(@RequestParam long mobno) {
        customerService.deleteCustomer(mobno);

    }

    @GetMapping("/find/customer")
    public ResponseEntity<Customer> findCustomer(@RequestParam
                                                     long mobno) {
        Customer c = customerService.findCustomer(mobno);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @PostMapping("/customer/SearchItemOrRestaurant")
    public ResponseEntity<List<Restaurant>> SearchItemOrRestaurant(@RequestParam long mobno, @RequestParam String SearchKey) {
        List<Restaurant> result = restaurantService.searchItemorRestaurant(mobno, SearchKey);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @PostMapping("/customer/addtocart")
//    public ResponseEntity<String> addtocart(@RequestParam long mobno, @RequestParam int Itemid, @RequestParam int quantity) {
//        customerService.addtocart(mobno, Itemid, quantity);
//        return new ResponseEntity<>("Added To Cart", HttpStatus.OK);
//
//    }

    @PostMapping("/customer/addtocartt")
    public ResponseEntity<CartItem> addtocartt(@RequestParam long mobno, @RequestParam int Itemid, @RequestParam int quantity) {
        CartItem addtocartt = customerService.addtocartt(mobno, Itemid, quantity);
        return new ResponseEntity<>(addtocartt, HttpStatus.OK);
    }

//    @GetMapping("/customer/getCart")
//    public ResponseEntity<List<CartItem>> getAllCart(@RequestParam long mobno) {
//        List<CartItem> allCart = customerService.getAllCart(mobno);
//        return new ResponseEntity<>(allCart, HttpStatus.OK);
//
//    }

//    @PostMapping("/customer/placeOrder")
//    public ResponseEntity<ResponseStructure<OrderNeedConsentDto>> placeOrder(@RequestParam long mobno, @RequestParam String PaymentType,
//                                                                             @RequestParam String AddressType
//            , @RequestParam String SpecialRequest) {
//        return customerService.placingOrder(mobno, PaymentType, AddressType, SpecialRequest);
//    }



    @PostMapping("/customer/denyPlacingOrder")
    public ResponseEntity<ResponseStructure<String>> denyPlacingOrder(@RequestParam int orderid) {
        return customerService.denyPlacingOrder(orderid);
//        return new ResponseEntity<>("Order Cancelled Successfully", HttpStatus.OK);
    }
    @DeleteMapping("/customer/removeItemFromCart")
    public ResponseEntity<ResponseStructure<String>> removeItemFromCart(@RequestParam long mobno,@RequestParam int itemid){
        return customerService.removeItemFromCart(mobno,itemid);
    }

    @GetMapping("/customer/getcart")
    public CartResponseDto getCart(@RequestParam long custmobno){
        return customerService.getCart(custmobno);
    }
    @PostMapping("/customer/placeOrder")
    public ResponseEntity<ResponseStructure<OrderNeedConsentDto>> placeOrder(@RequestParam long mobno, @RequestParam String PaymentType,
                                                                             @RequestParam String AddressType
            , @RequestParam String SpecialRequest,@RequestParam Integer couponId) {
        return customerService.placingOrder(mobno, PaymentType, AddressType, SpecialRequest,couponId);
    }



    @PostMapping("/customer/cancelOrder")
    public ResponseEntity<ResponseStructure<String>> cancleOrder(@RequestParam long mobno,@RequestParam int orderId){
        return customerService.cancelOrder(mobno,orderId);
    }

    @PostMapping("/customer/ConfirmPlacingOrder")
    public ResponseEntity<ResponseStructure<String>> confirmPlacingOrder(@RequestParam int orderid) {
        return  customerService.confirmPlacingOrder(orderid);
//        return new ResponseEntity<>("Order Placed Successfully", HttpStatus.OK);
    }

    @PostMapping("/customer/ConfirmPlacingOrderByOnline")
    public ResponseEntity<ResponseStructure<String>>  confirmByOnline(@RequestParam long mobno,@RequestParam int orderid){
        return customerService.confirmByOnlinePayment(mobno,orderid);
    }

    @PostMapping("/customer/OnlinepaymentSuccess")
    public ResponseEntity<ResponseStructure<String>> paymentSuccess(
            @RequestParam int orderId) {

        return customerService.OnlinepaymentSuccess(orderId);
    }
    @PostMapping("/customer/OnlinePaymentFailed")
    public ResponseEntity<ResponseStructure<String>> paymentFailed(
            @RequestParam int orderId) {

        return customerService.OnlinePaymentFailed(orderId);
    }


}

