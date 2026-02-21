

package com.alpha.foodorbit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import com.alpha.foodorbit.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByMobno(long mobno);

    Optional<Customer> findByMobno(long mobno);
}
