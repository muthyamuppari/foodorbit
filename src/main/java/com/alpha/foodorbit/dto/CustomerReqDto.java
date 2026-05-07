package com.alpha.foodorbit.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

public class CustomerReqDto {

    @NotBlank
    private String name;

    @Min(value = 1000000000L)
    @Max(value = 9999999999L)
    private long mobno;
    @Email
    private String mailid;

    @Pattern(regexp = "Male|Female|Other",message = "Gender must be Male, Female or Other")
    private  String gender;
@Valid
    private List<CustAddressReqDto> addresses;




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
