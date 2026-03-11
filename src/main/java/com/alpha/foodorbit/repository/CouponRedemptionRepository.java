package com.alpha.foodorbit.repository;

import com.alpha.foodorbit.entities.Coupon;
import com.alpha.foodorbit.entities.CouponRedemption;
import com.alpha.foodorbit.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRedemptionRepository extends JpaRepository<CouponRedemption,Integer> {

    boolean existsByCustomerAndCoupon(Customer customer, Coupon coupon);
}
