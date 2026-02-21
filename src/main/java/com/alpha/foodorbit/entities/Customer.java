package com.alpha.foodorbit.entities;

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

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany( cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> cart;

    @OneToMany(cascade=CascadeType.ALL)
    private List<CartItem> cartItems;

    public Customer(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Customer(){
    	
    }

    // All-fields constructor
    public Customer( String name, long mobno, String mailid, String gender,
                    Address address, List<Order> orders, List<Item> cart) {
    
        this.name = name;
        this.mobno = mobno;
        this.mailid = mailid;
        this.gender = gender;
        this.address = address;
        this.orders = orders;
        this.cart = cart;
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

    public long getMobno() {
        return mobno;
    }

    public void setMobno( long mobno) {
        this.mobno = mobno;
    }

    public String getMailid() {
        return mailid;
    }

    public void setMailid(String mailid) {
        this.mailid = mailid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Item> getCart() {
        return cart;
    }

    public void setCart(List<Item> cart) {
        this.cart = cart;
    }

    // toString override
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mobno='" + mobno + '\'' +
                ", mailid='" + mailid + '\'' +
                ", gender='" + gender + '\'' +
                ", address=" + address +
                ", orders=" + orders +
                ", cart=" + cart +
                '}';
    }
}
