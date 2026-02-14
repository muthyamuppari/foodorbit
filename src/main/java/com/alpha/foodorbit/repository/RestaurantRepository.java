package com.alpha.foodorbit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.foodorbit.entities.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

	public Optional<Restaurant> findByMailid(String mailid);
	
}
