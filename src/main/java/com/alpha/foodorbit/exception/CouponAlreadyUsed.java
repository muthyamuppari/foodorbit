package com.alpha.foodorbit.exception;

public class CouponAlreadyUsed extends RuntimeException {
    public CouponAlreadyUsed(String message) {
        super(message);
    }
}
