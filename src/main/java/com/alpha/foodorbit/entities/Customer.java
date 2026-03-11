package com.alpha.foodorbit.entities;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
     
    @Column(unique=true)
    private long mobno;
    @Column(unique=true)
    private String mailid;

    private String gender;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private List<Address> address;

    @OneToMany( cascade = CascadeType.ALL)
    private List<Order> orders;

//    @OneToMany(cascade = CascadeType.ALL)
//    private List<Item> cart;

    @OneToMany(mappedBy = "customer",cascade=CascadeType.ALL,orphanRemoval = true)
    private List<CartItem> cartItems;


    @OneToMany(mappedBy = "customer")
    private List<CouponRedemption> couponRedemptions=new ArrayList<>();

    private double penalty;
    private double wallet;


    public Customer(List<Address> address, List<CartItem> cartItems, List<CouponRedemption> couponRedemptions,
                    String gender, int id, String mailid, long mobno, String name,
                    List<Order> orders, double penalty, double wallet) {
        this.address = address;
        this.cartItems = cartItems;
        this.couponRedemptions = couponRedemptions;
        this.gender = gender;
        this.id = id;
        this.mailid = mailid;
        this.mobno = mobno;
        this.name = name;
        this.orders = orders;
        this.penalty = penalty;
        this.wallet = wallet;
    }

    public Customer() {
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public List<CouponRedemption> getCouponRedemptions() {
        return couponRedemptions;
    }

    public void setCouponRedemptions(List<CouponRedemption> couponRedemptions) {
        this.couponRedemptions = couponRedemptions;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }
}
