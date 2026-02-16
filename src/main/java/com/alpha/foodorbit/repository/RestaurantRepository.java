package com.alpha.foodorbit.repository;

import com.alpha.foodorbit.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {

    Restaurant findByMobno(long mobno);
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.foodorbit.entities.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

	public Optional<Restaurant> findByMailid(String mailid);
	
}
