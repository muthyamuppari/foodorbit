package com.alpha.foodorbit.exception;

import com.alpha.foodorbit.entities.DeliveryPartner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> handleSqlIntegrity(SQLIntegrityConstraintViolationException ex){

        return new ResponseEntity<>("Duplicate entry or Constraint Voilation.Please Check your Input Bro", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<String> handleItemNotFound(ItemNotFoundException ex){
        return new ResponseEntity<>("Item Not Found with this ID",HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DifferentRestaurantItem.class)
    public ResponseEntity<String> handleDifferentRest(DifferentRestaurantItem ex){
        return new ResponseEntity<>("Cannot Add Item from Different Restaurant Brother",HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CustomerNotFound.class)
    public ResponseEntity<String> handleCustomer(CustomerNotFound ex){
        return new ResponseEntity<>("Customer Not Found with given ID Brother",HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> handeOrder(OrderNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DeliveryPartnerNotFound.class)
    public ResponseEntity<String> handleDeliveryPartner(DeliveryPartnerNotFound ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidOtpException.class)
    public ResponseEntity<String> handleInvalidOtp(InvalidOtpException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(RestaurantNotFound.class)
    public ResponseEntity<String> handleRestNotFound(RestaurantNotFound ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CouponNotFound.class)
    public ResponseEntity<String> handleCouponNotFound(CouponNotFound ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CouponExpired.class)
    public ResponseEntity<String> handleCouponNotFound(CouponExpired ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CouponLimitExhausted.class)
    public ResponseEntity<String> handleCouponNotFound(CouponLimitExhausted ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CouponAlreadyUsed.class)
    public ResponseEntity<String> handleCouponNotFound(CouponAlreadyUsed ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }






}
