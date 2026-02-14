package com.alpha.foodorbit.dto;

import com.alpha.foodorbit.special.LocationCordinate;

public class RestaurantReqDto {

    private String name;
    private long mobno;
    private String mailid;
    private LocationCordinate locationCordinate;
    private String description;
    private double packagingFees;
    private String type;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocationCordinate getLocationCordinate() {
        return locationCordinate;
    }

    public void setLocationCordinate(LocationCordinate locationCordinate) {
        this.locationCordinate = locationCordinate;
    }

    public String getMailid() {
        return mailid;
    }

    public void setMailid(String mailid) {
        this.mailid = mailid;
    }

    public long getMobno() {
        return mobno;
    }

    public void setMobno(long mobno) {
        this.mobno = mobno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPackagingFees() {
        return packagingFees;
    }

    public void setPackagingFees(double packagingFees) {
        this.packagingFees = packagingFees;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RestaurantReqDto(String description, LocationCordinate locationCordinate, String mailid, long mobno, String name, double packagingFees, String type) {
        this.description = description;
        this.locationCordinate = locationCordinate;
        this.mailid = mailid;
        this.mobno = mobno;
        this.name = name;
        this.packagingFees = packagingFees;
        this.type = type;
    }

    public RestaurantReqDto() {
    }
}
