package com.alpha.foodorbit.exception;

public class CouponLimitExhausted extends RuntimeException {
    public CouponLimitExhausted(String message) {
        super(message);
    }
}
