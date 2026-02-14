package com.alpha.foodorbit.dto;

public class DeliveryPartnerDto {

    private String name;
    private String mobno;
    private String email;
    private String vehicleNo;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public DeliveryPartnerDto(String email, String mobno, String name, String vehicleNo) {
        this.email = email;
        this.mobno = mobno;
        this.name = name;
        this.vehicleNo = vehicleNo;
    }

    public DeliveryPartnerDto() {
    }
}
