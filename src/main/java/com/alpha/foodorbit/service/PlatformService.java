package com.alpha.foodorbit.service;

import com.alpha.foodorbit.entities.Coupon;
import com.alpha.foodorbit.exception.CouponNotFound;
import com.alpha.foodorbit.repository.CouponRepository;
import com.alpha.foodorbit.special.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatformService {

    @Autowired
    private CouponRepository couponRepository;

    public ResponseEntity<ResponseStructure<Coupon>> createCoupon(Coupon coupon){
        couponRepository.save(coupon);
        ResponseStructure<Coupon> rs=new ResponseStructure<>();
        rs.setData(coupon);
        rs.setMessage("Coupon Added Successfully");
        rs.setStatuscode(200);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Coupon>> findCouponById(int id) {
        ResponseStructure<Coupon> rs = new ResponseStructure<>();

        Coupon coupon = couponRepository.findById(id).orElseThrow(() -> new CouponNotFound("Coupon not found"));
        rs.setData(coupon);
        rs.setMessage("Coupon Found");
        rs.setStatuscode(200);
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Coupon>> updateCoupon(int id, Coupon updatedCoupon){

        ResponseStructure<Coupon> rs = new ResponseStructure<>();

        Coupon existingCoupon = couponRepository.findById(id).orElseThrow(()->new CouponNotFound("Coupon not found"));

            existingCoupon.setName(updatedCoupon.getName());
            existingCoupon.setType(updatedCoupon.getType());
            existingCoupon.setStatus(updatedCoupon.getStatus());
            existingCoupon.setOffer(updatedCoupon.getOffer());
            existingCoupon.setMinOrderPrice(updatedCoupon.getMinOrderPrice());
            existingCoupon.setMaxReedemPrice(updatedCoupon.getMaxReedemPrice());
            existingCoupon.setMaxCoupons(updatedCoupon.getMaxCoupons());
            existingCoupon.setExpiryDate(updatedCoupon.getExpiryDate());

            couponRepository.save(existingCoupon);

            rs.setData(existingCoupon);
            rs.setMessage("Coupon Updated Successfully");
            rs.setStatuscode(200);

            return new ResponseEntity<>(rs, HttpStatus.OK);


    }

    public ResponseEntity<ResponseStructure<Coupon>> deleteCoupon(int id){

        ResponseStructure<Coupon> rs = new ResponseStructure<>();

        Coupon coupon = couponRepository.findById(id).orElseThrow(()->new CouponNotFound("Coupon not found"));
            couponRepository.delete(coupon);

            rs.setData(coupon);
            rs.setMessage("Coupon Deleted Successfully");
            rs.setStatuscode(200);

            return new ResponseEntity<>(rs, HttpStatus.OK);


    }
    public ResponseEntity<ResponseStructure<List<Coupon>>> findAllCoupons(){

        ResponseStructure<List<Coupon>> rs = new ResponseStructure<>();

        List<Coupon> coupons = couponRepository.findAll();

        rs.setData(coupons);
        rs.setMessage("All Coupons Retrieved");
        rs.setStatuscode(200);

        return new ResponseEntity<>(rs, HttpStatus.OK);
    }
}
