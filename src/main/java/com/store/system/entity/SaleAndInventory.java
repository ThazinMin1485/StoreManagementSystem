package com.store.system.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "sale&inventory")
public class SaleAndInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "good_id")
    private Good good;

    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;

    @Column(nullable = false)
    String status;

    @Column(nullable = false)
    Long sold_quantity;

    @Column(nullable = false)
    Long left_quantity;

    @Column(nullable = false)
    Date dateAndTime;

    @Column(nullable = false)
    Boolean del_flag;

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

    public Long getSold_quantity() {
        return sold_quantity;
    }

    public void setSold_quantity(Long sold_quantity) {
        this.sold_quantity = sold_quantity;
    }

    public Long getLeft_quantity() {
        return left_quantity;
    }

    public void setLeft_quantity(Long left_quantity) {
        this.left_quantity = left_quantity;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Boolean getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(Boolean del_flag) {
        this.del_flag = del_flag;
    }
}
