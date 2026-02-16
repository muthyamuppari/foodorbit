package com.alpha.foodorbit.service;

import com.alpha.foodorbit.dto.DeliveryPartnerDto;
import com.alpha.foodorbit.dto.RestaurantReqDto;
import com.alpha.foodorbit.entities.DeliveryPartner;
import com.alpha.foodorbit.repository.DeliveryPartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;

@Service
public class DeliveryPartnerService {

    @Autowired
    private DeliveryPartnerRepository deliveryPartnerRepository;


    public void adding(DeliveryPartnerDto deliveryPartnerDto) {
        DeliveryPartner deliveryPartner=new DeliveryPartner();
        deliveryPartner.setName(deliveryPartnerDto.getName());
        deliveryPartner.setMobno(deliveryPartnerDto.getMobno());
        deliveryPartner.setEmail(deliveryPartnerDto.getEmail());
        deliveryPartner.setVehicleNo(deliveryPartnerDto.getVehicleNo());
        deliveryPartnerRepository.save(deliveryPartner);
    }

    public void deletePartner(long mobno){
        DeliveryPartner d= deliveryPartnerRepository.findByMobno(mobno);
        deliveryPartnerRepository.delete(d);

    }

    public DeliveryPartner findDeliveryPartner(long mobno) {

        return deliveryPartnerRepository.findByMobno(mobno);
    }

}
