package com.alpha.foodorbit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.foodorbit.entities.Order;

@Repository
public interface OrderRepository  extends JpaRepository<Order, Integer>{
	


}
