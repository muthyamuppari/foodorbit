package com.alpha.foodorbit.repository;

import com.alpha.foodorbit.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Integer> {
}
