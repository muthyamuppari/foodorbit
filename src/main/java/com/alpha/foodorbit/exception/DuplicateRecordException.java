package com.alpha.foodorbit.exception;

public class DuplicateRecordException extends  RuntimeException{

    public DuplicateRecordException(String message){
        super(message);
    }
}
