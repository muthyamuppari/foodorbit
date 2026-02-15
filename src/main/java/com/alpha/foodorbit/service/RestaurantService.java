package com.alpha.foodorbit.service;

import com.alpha.foodorbit.dto.RestaurantReqDto;
import com.alpha.foodorbit.entities.Restaurant;
import com.alpha.foodorbit.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public void adding(RestaurantReqDto restaurantReqDto) {

        Restaurant restaurant=new Restaurant();
        restaurant.setName(restaurantReqDto.getName());
        restaurant.setMobno(restaurantReqDto.getMobno());
        restaurant.setMailid(restaurantReqDto.getMailid());
        //error line
//        restaurant.setAddress(restaurantReqDto.getLocationCordinate());
        restaurant.setAddress(null);


        restaurant.setDescription(restaurantReqDto.getDescription());
        restaurant.setPackagingFees(restaurantReqDto.getPackagingFees());
        restaurant.setType(restaurantReqDto.getType());

        restaurantRepository.save(restaurant);

    }

    public void deleteRestaurant(long mobno) {
          Restaurant r=  restaurantRepository.findByMobno(mobno);
          restaurantRepository.delete(r);
    }

    public Restaurant findRestaurant(long mobno) {
        return restaurantRepository.findByMobno(mobno);
    }
}
