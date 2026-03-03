package com.alpha.foodorbit.exception;

public class ItemNotFoundException  extends RuntimeException{

    public ItemNotFoundException(String message){
        super(message);
    }
}
