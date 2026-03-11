package com.alpha.foodorbit.controller;

import com.alpha.foodorbit.entities.Coupon;
import com.alpha.foodorbit.service.PlatformService;
import com.alpha.foodorbit.special.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CouponController {

@Autowired
private PlatformService platformService;

    @PostMapping("/platform/createCoupon")
    public ResponseEntity<ResponseStructure<Coupon>> createCoupon(@RequestBody Coupon coupon){
        return platformService.createCoupon(coupon);

    }
    @GetMapping("/platform/findCoupon/{id}")
    public ResponseEntity<ResponseStructure<Coupon>> findCoupon(@PathVariable int id){
        return platformService.findCouponById(id);
    }

    @PutMapping("/platform/updateCoupon/{id}")
    public ResponseEntity<ResponseStructure<Coupon>> updateCoupon(
            @PathVariable int id,
            @RequestBody Coupon coupon){

        return platformService.updateCoupon(id, coupon);
    }

    @DeleteMapping("/platform/deleteCoupon/{id}")
    public ResponseEntity<ResponseStructure<Coupon>> deleteCoupon(@PathVariable int id){
        return platformService.deleteCoupon(id);
    }

    @GetMapping("/platform/findAllCoupons")
    public ResponseEntity<ResponseStructure<List<Coupon>>> findAllCoupons(){
        return platformService.findAllCoupons();
    }



}
