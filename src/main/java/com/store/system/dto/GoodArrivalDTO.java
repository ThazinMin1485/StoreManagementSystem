package com.store.system.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.store.system.entity.Good;
import com.store.system.entity.GoodArrival;
import com.store.system.validator.QuantityOrKgRequired;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@QuantityOrKgRequired
public class GoodArrivalDTO {
    Long id;

    @NotNull(message = "Good cannot be empty")
    private Good good;

    Long quantity;

    Double kg;

    @NotNull(message = "Price cannot be empty")
    Double purchasePrice;

    @NotNull(message = "Transportion Cost canot be empty")
    Double transportationCost;

    Double overhead;

    @NotNull(message = "Please fill Arrival Time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate arrivalTime;

    public GoodArrivalDTO() {
        super();
    }

    public GoodArrivalDTO(GoodArrival g) {
        this.id = g.getId();
        this.kg = g.getKg();
        this.quantity = g.getQuantity();
        this.good = g.getGood();
        this.arrivalTime = g.getArrivalTime();
        this.overhead = g.getOverhead();
        this.purchasePrice = g.getPurchasePrice();
        this.transportationCost = g.getTransportationCost();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "Good cannot be empty") Good getGood() {
        return good;
    }

    public void setGood(@NotNull(message = "Good cannot be empty") Good good) {
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

    public @NotNull(message = "Price cannot be empty") Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(@NotNull(message = "Price cannot be empty") Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public @NotNull(message = "Transportion Cost canot be empty") Double getTransportationCost() {
        return transportationCost;
    }

    public void setTransportationCost(@NotNull(message = "Transportion Cost canot be empty") Double transportationCost) {
        this.transportationCost = transportationCost;
    }

    public Double getOverhead() {
        return overhead;
    }

    public void setOverhead(Double overhead) {
        this.overhead = overhead;
    }

    public @NotNull(message = "Please fill Arrival Time") LocalDate getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(@NotNull(message = "Please fill Arrival Time") LocalDate arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
