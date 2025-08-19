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

    @Column(nullable = false)
    String good_name;

    Long quantity;

    Double kg;

    @Column(nullable = false)
    Boolean del_flag;

    public Good() {
        super();
    }

    public Good(GoodDTO dto) {
        this.id = dto.getId();
        this.good_name = dto.getGood_name();
        this.category = dto.getCategory();
        this.kg = dto.getKg();
        this.del_flag = false;
        this.quantity = dto.getQuantity();
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getGood_name() {
        return good_name;
    }

    public void setGood_name(String good_name) {
        this.good_name = good_name;
    }

    public Boolean getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(Boolean del_flag) {
        this.del_flag = del_flag;
    }

    public Double getKg() {
        return kg;
    }

    public void setKg(Double kg) {
        this.kg = kg;
    }
}
