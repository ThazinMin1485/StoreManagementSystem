package com.store.system.entity;

import com.store.system.dto.VoucherDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "voucher")
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voucher_id")
    Long id;

    @Column(nullable = false, name = "voucher_no")
    String voucherNo;

    Double tax;

    @Column(name = "total_price")
    Double totalPrice;

    @Column(nullable = false, name = "del_flag")
    Boolean delFlag;

    public Voucher(){
        super();
    }

    public Voucher(VoucherDTO dto) {
        this.id = dto.getId();
        this.tax = dto.getTax();
        this.voucherNo = dto.getVoucherNo();
        this.totalPrice = dto.getTotalPrice();
        this.delFlag = dto.getDelFlag();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }
}
