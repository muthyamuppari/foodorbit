package com.alpha.foodorbit.exception;

public class CouponExpired extends RuntimeException {
    public CouponExpired(String message) {
        super(message);
    }
}
