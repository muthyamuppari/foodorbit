package com.alpha.foodorbit.exception;

public class DifferentRestaurantItem extends RuntimeException{
    public DifferentRestaurantItem(String message){
        super(message);
    }
}
