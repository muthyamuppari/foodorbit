package com.alpha.foodorbit.entities;

import jakarta.persistence.*;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
  
    @Column(unique=true)
    private String name;

    private String description;

    private double price;

   
    private String unit;

   
    private String type;

    
    private String availability;

    private double rating;

    // image URL or path
    private String image;

 
    private int numberOfServes;

    // No-arg constructor
    public Item() {
    }

   
    public Item( String name, String description, double price,
                String unit, String type, String availability,
                double rating, String image, int numberOfServes) {

      
        this.name = name;
        this.description = description;
        this.price = price;
        this.unit = unit;
        this.type = type;
        this.availability = availability;
        this.rating = rating;
        this.image = image;
        this.numberOfServes = numberOfServes;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getAvailability() { return availability; }
    public void setAvailability(String availability) { this.availability = availability; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public int getNumberOfServes() { return numberOfServes; }
    public void setNumberOfServes(int numberOfServes) { this.numberOfServes = numberOfServes; }

    // toString override
    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", unit='" + unit + '\'' +
                ", type=" + type +
                ", availability=" + availability +
                ", rating=" + rating +
                ", image='" + image + '\'' +
                ", numberOfServes=" + numberOfServes +
                '}';
    }
}
