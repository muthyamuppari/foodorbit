package com.alpha.foodorbit.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @Column(unique = true)
    private long mobno;

    @Column(unique = true)
    private String mailid;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Item> menu = new ArrayList<>();


    private String status;

    private double ratings;

    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Order> orders;


    private Double packagingFees;


    private String type;

    private double wallet;
    private double penalty;

    // No-arg constructor
    public Restaurant() {
    }

    // All-fields constructor


    public Restaurant(Address address, String description, int id, String mailid, List<Item> menu, long mobno,
                      String name, List<Order> orders, Double packagingFees, double penalty, double ratings,
                      String status, String type, double wallet) {
        this.address = address;
        this.description = description;
        this.id = id;
        this.mailid = mailid;
        this.menu = menu;
        this.mobno = mobno;
        this.name = name;
        this.orders = orders;
        this.packagingFees = packagingFees;
        this.penalty = penalty;
        this.ratings = ratings;
        this.status = status;
        this.type = type;
        this.wallet = wallet;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<Item> getMenu() {
        return menu;
    }

    public void setMenu(List<Item> menu) {
        this.menu = menu;
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

    public Double getPackagingFees() {
        return packagingFees;
    }

    public void setPackagingFees(Double packagingFees) {
        this.packagingFees = packagingFees;
    }

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    public double getRatings() {
        return ratings;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
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

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }
}