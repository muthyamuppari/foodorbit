package com.alpha.foodorbit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.foodorbit.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
	
	public Optional<Address> findByStreet(String street);
	

}
