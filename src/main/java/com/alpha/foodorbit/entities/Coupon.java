package com.alpha.foodorbit.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

@Entity
public class Coupon{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String name;
    private String type;
    private String status;
    private double offer;
    private double minOrderPrice;
    private double maxReedemPrice;
    private int maxCoupons;
    private LocalDate expiryDate;

    public Coupon(LocalDate expiryDate, Integer id, int maxCoupons,
                  double maxReedemPrice, double minOrderPrice, String name, double offer, String status, String type) {
        this.expiryDate = expiryDate;
        this.id = id;
        this.maxCoupons = maxCoupons;
        this.maxReedemPrice = maxReedemPrice;
        this.minOrderPrice = minOrderPrice;
        this.name = name;
        this.offer = offer;
        this.status = status;
        this.type = type;
    }

    public Coupon() {
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaxCoupons() {
        return maxCoupons;
    }

    public void setMaxCoupons(int maxCoupons) {
        this.maxCoupons = maxCoupons;
    }

    public double getMaxReedemPrice() {
        return maxReedemPrice;
    }

    public void setMaxReedemPrice(double maxReedemPrice) {
        this.maxReedemPrice = maxReedemPrice;
    }

    public double getMinOrderPrice() {
        return minOrderPrice;
    }

    public void setMinOrderPrice(double minOrderPrice) {
        this.minOrderPrice = minOrderPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getOffer() {
        return offer;
    }

    public void setOffer(double offer) {
        this.offer = offer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
