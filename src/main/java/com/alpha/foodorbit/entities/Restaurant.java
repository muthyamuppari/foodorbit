package com.alpha.foodorbit.entities;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @Column(unique=true)
    private long mobno;
    
    @Column(unique=true)
    private String mailid;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id")
    private List<Item> menu;

 
    private String status;

    private double ratings;

    private String description;

    @OneToMany( cascade = CascadeType.ALL)
    private List<Order> orders;

   
    private double packagingFees;

  
    private String type;

    // No-arg constructor
    public Restaurant() {
    }

    // All-fields constructor
    public Restaurant( String name, long mobno, String mailid,
                      Address address, List<Item> menu, String status,
                      double ratings, String description, List<Order> orders,
                      double packagingFees, String type) {

        this.name = name;
        this.mobno = mobno;
        this.mailid = mailid;
        this.address = address;
        this.menu = menu;
        this.status = status;
        this.ratings = ratings;
        this.description = description;
        this.orders = orders;
        this.packagingFees = packagingFees;
        this.type = type;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public long getMobno() { return mobno; }
    public void setMobno(long mobno) { this.mobno = mobno; }

    public String getMailid() { return mailid; }
    public void setMailid(String mailid) { this.mailid = mailid; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public List<Item> getMenu() { return menu; }
    public void setMenu(List<Item> menu) { this.menu = menu; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getRatings() { return ratings; }
    public void setRatings(double ratings) { this.ratings = ratings; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<Order> getOrders() { return orders; }
    public void setOrders(List<Order> orders) { this.orders = orders; }

    public double getPackagingFees() { return packagingFees; }
    public void setPackagingFees(double packagingFees) { this.packagingFees = packagingFees; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mobno='" + mobno + '\'' +
                ", mailid='" + mailid + '\'' +
                ", address=" + address +
                ", menu=" + menu +
                ", status=" + status +
                ", ratings=" + ratings +
                ", description='" + description + '\'' +
                ", orders=" + orders +
                ", packagingFees=" + packagingFees +
                ", type=" + type +
                '}';
    }
}