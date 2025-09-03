package com.store.system.dto;

import com.store.system.entity.Category;
import com.store.system.entity.Good;
import com.store.system.validator.QuantityXorKgRequired;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@QuantityXorKgRequired
public class GoodDTO {
    private Long id;

    @NotBlank(message = "Good Name cannot be empty")
    private String goodName;

    private Long quantity;

    private Double kg;

    private boolean del_flag;

    private boolean kilo;

    public GoodDTO() {
        super();
    }

    public GoodDTO(Good good) {
        this.id = good.getId();
        this.category = good.getCategory();
        this.goodName = good.getGoodName();
        this.quantity = good.getQuantity();
        this.kg = good.getKg();
        this.del_flag = good.isDel_flag();
        this.kilo = good.isKilo();
    }

    @NotNull(message = "Please select a category type")
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Good Name cannot be empty") String getGoodName() {
        return goodName;
    }

    public void setGoodName(@NotBlank(message = "Good Name cannot be empty") String goodName) {
        this.goodName = goodName;
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

    public boolean isDel_flag() {
        return del_flag;
    }

    public void setDel_flag(boolean del_flag) {
        this.del_flag = del_flag;
    }

    public boolean isKilo() {
        return kilo;
    }

    public void setKilo(boolean kilo) {
        this.kilo = kilo;
    }
}
