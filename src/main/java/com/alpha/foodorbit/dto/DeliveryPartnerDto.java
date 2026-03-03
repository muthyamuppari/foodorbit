package com.alpha.foodorbit.dto;

import com.alpha.foodorbit.special.LocationCordinate;

public class DeliveryPartnerDto {

    private String name;
    private String mobno;
    private String email;
    private String vehicleNo;
    private LocationCordinate locationCordinate;

    public DeliveryPartnerDto(String email, LocationCordinate locationCordinate, String mobno, String name, String vehicleNo) {
        this.email = email;
        this.locationCordinate = locationCordinate;
        this.mobno = mobno;
        this.name = name;
        this.vehicleNo = vehicleNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocationCordinate getLocationCordinate() {
        return locationCordinate;
    }

    public void setLocationCordinate(LocationCordinate locationCordinate) {
        this.locationCordinate = locationCordinate;
    }

    public String getMobno() {
        return mobno;
    }

    public void setMobno(String mobno) {
        this.mobno = mobno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public DeliveryPartnerDto() {
    }
}
