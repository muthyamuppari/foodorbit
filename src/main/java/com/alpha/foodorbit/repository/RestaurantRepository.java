package com.alpha.foodorbit.repository;

import com.alpha.foodorbit.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {

    Restaurant findByMobno(long mobno);
}
