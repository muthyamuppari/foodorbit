package com.alpha.foodorbit.repository;

import com.alpha.foodorbit.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Integer> {
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.foodorbit.entities.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
	
	public Optional<Item> findByName(String name);

}
