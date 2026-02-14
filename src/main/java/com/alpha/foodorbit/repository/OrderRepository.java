package com.alpha.foodorbit.repository;

import com.alpha.foodorbit.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
