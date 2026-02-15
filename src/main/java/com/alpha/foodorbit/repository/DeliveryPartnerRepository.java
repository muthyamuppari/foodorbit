package com.alpha.foodorbit.repository;

import com.alpha.foodorbit.entities.DeliveryPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryPartnerRepository extends JpaRepository<DeliveryPartner,Integer> {

    DeliveryPartner findByMobno(long mobno);
}
