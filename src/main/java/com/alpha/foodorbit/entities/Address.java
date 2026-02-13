package com.alpha.foodorbit.entities;

import jakarta.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
  
    @Column(unique=true)
    private String street;

    private String landmark;

    private String city;

    private String state;

    private String country;

    private int pincode;

    // No-arg constructor (required by JPA)
    public Address() {
    }

    // All-fields constructor (without id – best practice)
    public Address(String street, String landmark,
                   String city, String state, String country, int pincode) {
        this.street = street;
        this.landmark = landmark;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getLandmark() { return landmark; }
    public void setLandmark(String landmark) { this.landmark = landmark; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public int getPincode() { return pincode; }
    public void setPincode(int pincode) { this.pincode = pincode; }

    // toString override
    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", landmark='" + landmark + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", pincode=" + pincode +
                '}';
    }
}
