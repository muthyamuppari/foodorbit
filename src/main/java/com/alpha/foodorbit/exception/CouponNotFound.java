package com.alpha.foodorbit.exception;

public class CouponNotFound extends RuntimeException {
    public CouponNotFound(String message) {
        super(message);
    }
}
