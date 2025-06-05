package com.store.system.entity;

import jakarta.persistence.*;
import lombok.extern.slf4j.XSlf4j;

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
    @JoinColumn(name="voucher_id")
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
}
