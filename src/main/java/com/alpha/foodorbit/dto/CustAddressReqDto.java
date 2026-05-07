package com.alpha.foodorbit.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CustAddressReqDto {

    private String flatNumber;
    @NotBlank
    private String buildingName;
    @NotBlank
    private String street;
    @NotBlank
    private String city;
    @NotBlank
    private String state;
    @NotBlank
    private String pincode;
    @Pattern(regexp = "Home|Office|Other",message = "Address must be Home ,Office or Other")
    private String addressType;

    private Boolean isDefault;

    public CustAddressReqDto(String addressType, String buildingName, String city, String flatNumber, Boolean isDefault, String pincode, String state, String street) {
        this.addressType = addressType;
        this.buildingName = buildingName;
        this.city = city;
        this.flatNumber = flatNumber;
        this.isDefault = isDefault;
        this.pincode = pincode;
        this.state = state;
        this.street = street;
    }

    public CustAddressReqDto() {
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
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

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
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
}
