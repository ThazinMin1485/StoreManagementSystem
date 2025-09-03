package com.store.system.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.store.system.entity.Good;
import com.store.system.entity.GoodDetail;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class GoodDetailDTO {

    Long id;

    private Good good;

    Long quantity;

    Double g;

    Double percent;

    Double sellingPrice;

    String photo;

    @NotNull(message = "Please fill From Date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate fromDate;

    @NotNull(message = "Please fill To Date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate toDate;

    public GoodDetailDTO() {
        super();
    }

    public GoodDetailDTO(GoodDetail detail) {
        this.id = detail.getId();
        this.good = detail.getGood();
        this.g = detail.getG();
        this.quantity = detail.getQuantity();
        this.percent = detail.getPercent();
        this.photo = detail.getPhoto();
        this.sellingPrice = detail.getSellingPrice();
        this.fromDate = detail.getFromDate();
        this.toDate = detail.getToDate();
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

    public Double getG() {
        return g;
    }

    public void setG(Double g) {
        this.g = g;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
}
