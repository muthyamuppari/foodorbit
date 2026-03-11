package com.alpha.foodorbit.entities;

import jakarta.persistence.*;

@Entity
public class CouponRedemption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Coupon coupon;

    @ManyToOne
    private Customer customer;

    @OneToOne
    private Order order;

    public CouponRedemption(Coupon coupon, Customer customer, Integer id, Order order) {
        this.coupon = coupon;
        this.customer = customer;
        this.id = id;
        this.order = order;
    }

    public CouponRedemption() {
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
