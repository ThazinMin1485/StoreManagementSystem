package com.store.system.dto;

import com.store.system.entity.Good;
import com.store.system.entity.SaleAndInventory;
import com.store.system.entity.Voucher;

import java.time.LocalDate;

public class SaleAndInventoryDTO {
    Long id;

    private Good good;

    private Voucher voucher;

    String status;

    Long soldQuantity;

    Long leftQuantity;

    LocalDate dateAndTime;

    Boolean delFlag;

    public SaleAndInventoryDTO() {
        super();
    }

    public SaleAndInventoryDTO(SaleAndInventory inventory) {
        this.id = inventory.getId();
        this.good = inventory.getGood();
        this.voucher= inventory.getVoucher();
        this.dateAndTime = inventory.getDateAndTime();
        this.status = inventory.getStatus();
        this.soldQuantity = inventory.getSoldQuantity();
        this.leftQuantity = inventory.getLeftQuantity();
        this.delFlag = inventory.getDelFlag();
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

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(Long soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public Long getLeftQuantity() {
        return leftQuantity;
    }

    public void setLeftQuantity(Long leftQuantity) {
        this.leftQuantity = leftQuantity;
    }

    public LocalDate getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDate dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }
}
