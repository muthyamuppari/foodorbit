package com.alpha.foodorbit.repository;

import com.alpha.foodorbit.entities.DeliveryPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryPartnerRepository extends JpaRepository<DeliveryPartner,Integer> {

    Optional<DeliveryPartner> findByMobno(long mobno);
}
