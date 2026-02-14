package com.alpha.foodorbit.entities;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String status;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    @OneToOne
    private Customer customer;
    @ManyToMany
    private List<Order> orders;
    private double cost;
    @OneToMany
    private List<Item> items;
    private String pickupAddress;
    private String deliveryAddress;
    private String otp;
    @OneToOne
    private DeliveryPartner deliveryPartner;
    @OneToOne
    private Payment payment;
    private String estimatedTime;
    private double distance;
    private double discount;
    private String coupon;
    private String specialRequest;
    private String deliveryInstructions;
    private String date; // String type as requested

    // 🔹 Default Constructor
    public Order() {
    }

    // 🔹 Full Constructor (ALL fields)
    public Order(String status, Restaurant restaurant,
                 Customer customer, double cost, List<Item> items,
                 String pickupAddress, String deliveryAddress,
                 String otp, DeliveryPartner deliveryPartner,
                 Payment payment, String estimatedTime,
                 double distance, double discount, String coupon,
                 String specialRequest, String deliveryInstructions,
                 String date) {

        this.status = status;
        this.restaurant = restaurant;
        this.customer = customer;
        this.cost = cost;
        this.items = items;
        this.pickupAddress = pickupAddress;
        this.deliveryAddress = deliveryAddress;
        this.otp = otp;
        this.deliveryPartner = deliveryPartner;
        this.payment = payment;
        this.estimatedTime = estimatedTime;
        this.distance = distance;
        this.discount = discount;
        this.coupon = coupon;
        this.specialRequest = specialRequest;
        this.deliveryInstructions = deliveryInstructions;
        this.date = date;
    }

    // 🔹 Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public DeliveryPartner getDeliveryPartner() {
        return deliveryPartner;
    }

    public void setDeliveryPartner(DeliveryPartner deliveryPartner) {
        this.deliveryPartner = deliveryPartner;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getSpecialRequest() {
        return specialRequest;
    }

    public void setSpecialRequest(String specialRequest) {
        this.specialRequest = specialRequest;
    }

    public String getDeliveryInstructions() {
        return deliveryInstructions;
    }

    public void setDeliveryInstructions(String deliveryInstructions) {
        this.deliveryInstructions = deliveryInstructions;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // 🔹 toString Override
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status=" + status +
                ", restaurant=" + restaurant +
                ", customer=" + customer +
                ", cost=" + cost +
                ", pickupAddress='" + pickupAddress + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", deliveryPartner=" + deliveryPartner +
                ", payment=" + payment +
                ", estimatedTime='" + estimatedTime + '\'' +
                ", distance=" + distance +
                ", discount=" + discount +
                ", coupon=" + coupon +
                ", specialRequest='" + specialRequest + '\'' +
                ", deliveryInstructions='" + deliveryInstructions + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
