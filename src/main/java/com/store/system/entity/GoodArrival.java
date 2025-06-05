package com.store.system.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "good_arrival")
public class GoodArrival {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "good_id")
    private Good good;

    Long quantity;

    Double kg;

    @Column(name = "purchase_price", nullable = false)
    Double purchasePrice;

    @Column(name = "transportation_cost", nullable = false)
    Double transportationCost;

    Double overhead;

    Date arrivalTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getKg() {
        return kg;
    }

    public void setKg(Double kg) {
        this.kg = kg;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Double getTransportationCost() {
        return transportationCost;
    }

    public void setTransportationCost(Double transportationCost) {
        this.transportationCost = transportationCost;
    }

    public Double getOverhead() {
        return overhead;
    }

    public void setOverhead(Double overhead) {
        this.overhead = overhead;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
