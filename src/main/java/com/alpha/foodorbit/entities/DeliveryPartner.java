package com.alpha.foodorbit.entities;


import java.util.List;

import jakarta.persistence.*;

@Entity
public class DeliveryPartner {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @Column(unique=true)
    private String mobno;
    @Column(unique=true)
    private String email;
    private double rating;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "deliveryPartner")
    private List<Order> orders;
    @Column(unique=true)
    private String vehicleNo;
    private String status;

    private double penalty;
    private double wallet;

    public DeliveryPartner() {
    }

    public DeliveryPartner(Address address, String email,
                           int id, String mobno, String name, List<Order> orders, double penalty,
                           double rating, String status, String vehicleNo, double wallet) {
        this.address = address;
        this.email = email;
        this.id = id;
        this.mobno = mobno;
        this.name = name;
        this.orders = orders;
        this.penalty = penalty;
        this.rating = rating;
        this.status = status;
        this.vehicleNo = vehicleNo;
        this.wallet = wallet;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }
}
