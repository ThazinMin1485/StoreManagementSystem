package com.store.system.entity;

import com.store.system.dto.GoodDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "good")
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "good_id")
    Long id;

    @ManyToOne()
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false, name = "good_name")
    String goodName;

    Long quantity;

    Double kg;

    @Column(nullable = false)
    boolean del_flag;

    @Column(nullable = false)
    boolean kilo;

    public Good() {
        super();
    }

    public Good(GoodDTO dto) {
        this.id = dto.getId();
        this.goodName = dto.getGoodName();
        this.category = dto.getCategory();
        this.kg = dto.getKg();
        this.del_flag = false;
        this.quantity = dto.getQuantity();
        this.kilo = dto.isKilo();
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public boolean isKilo() {
        return kilo;
    }

    public void setKilo(boolean kilo) {
        this.kilo = kilo;
    }
}
