package com.store.system.dto;

import com.store.system.entity.Voucher;

public class VoucherDTO {
    Long id;

    String voucherNo;

    Double tax;

    Double totalPrice;

    Boolean delFlag;

    public VoucherDTO() {
        super();
    }

    public VoucherDTO(Voucher voucher) {
        this.id = voucher.getId();
        this.voucherNo = voucher.getVoucherNo();
        this.tax = voucher.getTax();
        this.totalPrice = voucher.getTotalPrice();
        this.delFlag = voucher.getDelFlag();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
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
