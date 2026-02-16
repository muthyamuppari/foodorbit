package com.alpha.foodorbit.repository;

import com.alpha.foodorbit.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.foodorbit.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
	
	public Optional<Address> findByStreet(String street);
	

}
