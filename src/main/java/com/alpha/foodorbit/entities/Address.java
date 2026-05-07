package com.alpha.foodorbit.entities;

import jakarta.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
  
//    @Column(unique=true)
//    private String street;
//
//    private String landmark;
//
//    private String city;
//
//    private String state;
//
//    private String country;
//
//    private int pincode;
    private  Double latitude;
    private Double longitude;
    //House Details
    private String flatNumber;
    private String floor;
    private String buildingName;
    private String street;
    //Area Details
    private String area;
    private String landmark;
    private String city;
    private String district;
    private String state;
    private String country;
    private String pincode;
    //Metadata
    private  String addressType;
    private Boolean isDefault;


    public Address(String addressType, String area, String buildingName, String city, String country, String district, String flatNumber, String floor, Integer id, Boolean isDefault, String landmark, Double latitude, Double longitude, String pincode, String state, String street) {
        this.addressType = addressType;
        this.area = area;
        this.buildingName = buildingName;
        this.city = city;
        this.country = country;
        this.district = district;
        this.flatNumber = flatNumber;
        this.floor = floor;
        this.id = id;
        this.isDefault = isDefault;
        this.landmark = landmark;
        this.latitude = latitude;
        this.longitude = longitude;
        this.pincode = pincode;
        this.state = state;
        this.street = street;
    }

    public Address() {
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressType='" + addressType + '\'' +
                ", id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", flatNumber='" + flatNumber + '\'' +
                ", floor='" + floor + '\'' +
                ", buildingName='" + buildingName + '\'' +
                ", street='" + street + '\'' +
                ", area='" + area + '\'' +
                ", landmark='" + landmark + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", pincode=" + pincode +
                ", isDefault=" + isDefault +
                '}';
    }
}
