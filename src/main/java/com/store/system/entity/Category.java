package com.store.system.entity;

import com.store.system.dto.CategoryDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    Long id;

    @Column(name = "category_name",nullable = false)
    String categoryName;

    @Column(nullable = false)
    Boolean del_flag;

    public Category(){
        super();
    }

    public Category(CategoryDTO dto) {
        this.id = dto.getId();
        this.categoryName = dto.getCategoryName();
        this.del_flag = dto.getDel_flag();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Boolean getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(Boolean del_flag) {
        this.del_flag = del_flag;
    }
}
