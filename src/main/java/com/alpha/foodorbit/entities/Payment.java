package com.alpha.foodorbit.entities;
import jakarta.persistence.*;


@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private double amount;


    private String type;

   
    private String status;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private String stripePaymentId;

    public Payment(double amount, int id, Order order, String status, String stripePaymentId, String type) {
        this.amount = amount;
        this.id = id;
        this.order = order;
        this.status = status;
        this.stripePaymentId = stripePaymentId;
        this.type = type;
    }

    public Payment() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStripePaymentId() {
        return stripePaymentId;
    }

    public void setStripePaymentId(String stripePaymentId) {
        this.stripePaymentId = stripePaymentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
