package com.alpha.foodorbit.repository;

import com.alpha.foodorbit.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.foodorbit.entities.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	
       

}
