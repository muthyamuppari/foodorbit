package com.alpha.foodorbit.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
//        @ManyToMany
//    private List<Order> orders;

    @OneToMany
    private List<Item> items;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pickup_address_id")
    private Address pickupAddress;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_address_id")
    private Address deliveryAddress;
    private int otp;
    @ManyToOne
    @JoinColumn(name = "delivery_partner_id")
    private DeliveryPartner deliveryPartner;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;
    private String estimatedTime;

    private Double distance;
    private double discount;
//    private String coupon;
    private String specialRequest;
    private String deliveryInstructions;
    private LocalDateTime date;

    private double orderCost;
    private double delivery_charges;
    private double packagingFees;
    private double tax;
    private double platformFees;
    private double totalCost;
    @ManyToOne
    private Coupon coupon;

    @OneToOne(mappedBy = "order")
    private CouponRedemption couponRedemption;

    public Order(Coupon coupon, CouponRedemption couponRedemption, Customer customer, LocalDateTime date, double delivery_charges,
                 Address deliveryAddress, String deliveryInstructions, DeliveryPartner deliveryPartner, double discount, Double distance,
                 String estimatedTime, int id, List<Item> items, double orderCost, int otp, double packagingFees, Payment payment,
                 Address pickupAddress, double platformFees, Restaurant restaurant, String specialRequest, String status, double tax,
                 double totalCost) {
        this.coupon = coupon;
        this.couponRedemption = couponRedemption;
        this.customer = customer;
        this.date = date;
        this.delivery_charges = delivery_charges;
        this.deliveryAddress = deliveryAddress;
        this.deliveryInstructions = deliveryInstructions;
        this.deliveryPartner = deliveryPartner;
        this.discount = discount;
        this.distance = distance;
        this.estimatedTime = estimatedTime;
        this.id = id;
        this.items = items;
        this.orderCost = orderCost;
        this.otp = otp;
        this.packagingFees = packagingFees;
        this.payment = payment;
        this.pickupAddress = pickupAddress;
        this.platformFees = platformFees;
        this.restaurant = restaurant;
        this.specialRequest = specialRequest;
        this.status = status;
        this.tax = tax;
        this.totalCost = totalCost;
    }

    public Order() {
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public CouponRedemption getCouponRedemption() {
        return couponRedemption;
    }

    public void setCouponRedemption(CouponRedemption couponRedemption) {
        this.couponRedemption = couponRedemption;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getDelivery_charges() {
        return delivery_charges;
    }

    public void setDelivery_charges(double delivery_charges) {
        this.delivery_charges = delivery_charges;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryInstructions() {
        return deliveryInstructions;
    }

    public void setDeliveryInstructions(String deliveryInstructions) {
        this.deliveryInstructions = deliveryInstructions;
    }

    public DeliveryPartner getDeliveryPartner() {
        return deliveryPartner;
    }

    public void setDeliveryPartner(DeliveryPartner deliveryPartner) {
        this.deliveryPartner = deliveryPartner;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public double getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(double orderCost) {
        this.orderCost = orderCost;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public double getPackagingFees() {
        return packagingFees;
    }

    public void setPackagingFees(double packagingFees) {
        this.packagingFees = packagingFees;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Address getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(Address pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public double getPlatformFees() {
        return platformFees;
    }

    public void setPlatformFees(double platformFees) {
        this.platformFees = platformFees;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getSpecialRequest() {
        return specialRequest;
    }

    public void setSpecialRequest(String specialRequest) {
        this.specialRequest = specialRequest;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}

