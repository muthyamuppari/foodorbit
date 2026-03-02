package com.alpha.foodorbit.dto;


import java.util.ArrayList;
import java.util.List;

public class CustomerReqDto {

    private String name;
    private long mobno;
    private String mailid;
    private  String gender;

    private List<CustAddressReqDto> addresses;

//    public CustomerReqDto(List<CustAddressReqDto> addresses) {
//        this.addresses = addresses;
//    }
//
//    public List<CustAddressReqDto> getAddresses() {
//        return addresses;
//    }
//
//    public void setAddresses(List<CustAddressReqDto> addresses) {
//        this.addresses = addresses;
//    }


    public List<CustAddressReqDto> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<CustAddressReqDto> addresses) {
        this.addresses = addresses;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public CustomerReqDto(String gender, String mailid, long mobno, String name) {
        this.gender = gender;
        this.mailid = mailid;
        this.mobno = mobno;
        this.name = name;
    }




    public CustomerReqDto() {
    }
}
