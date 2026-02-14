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
    private String address;
    @OneToOne
    private List<Order> orders;
    @Column(unique=true)
    private String vehicleNo;
    private String status;

    // Default Constructor
    public DeliveryPartner() {
    }

    // Parameterized Constructor
    public DeliveryPartner( String name, String mobno, String email,
                           double rating, String address, List<Order> orders,
                           String vehicleNo, String status) {
       
        this.name = name;
        this.mobno = mobno;
        this.email = email;
        this.rating = rating;
        this.address = address;
        this.orders = orders;
        this.vehicleNo = vehicleNo;
        this.status = status;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobno() {
        return mobno;
    }

    public void setMobno(String mobno) {
        this.mobno = mobno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   
    @Override
    public String toString() {
        return "DeliveryPartner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mobno='" + mobno + '\'' +
                ", email='" + email + '\'' +
                ", rating=" + rating +
                ", address='" + address + '\'' +
                ", vehicle=" + vehicleNo +
                ", status=" + status +
            
                '}';
    }
    
}
