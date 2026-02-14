package com.alpha.foodorbit.repository;

import com.alpha.foodorbit.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
}
