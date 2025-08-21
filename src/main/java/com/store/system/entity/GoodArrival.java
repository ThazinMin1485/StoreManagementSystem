package com.store.system.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.store.system.dto.GoodArrivalDTO;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate arrivalTime;

    public GoodArrival() {
        super();
    }

    public GoodArrival(GoodArrivalDTO dto) {
        this.id = dto.getId();
        this.arrivalTime = dto.getArrivalTime();
        this.good = dto.getGood();
        this.kg =dto.getKg();
        this.quantity = dto.getQuantity();
        this.overhead = dto.getOverhead();
        this.purchasePrice = dto.getPurchasePrice();
        this.transportationCost = dto.getTransportationCost();
    }

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

    public LocalDate getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDate arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
