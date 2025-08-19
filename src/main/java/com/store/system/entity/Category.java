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

    @Column(nullable = false)
    String category_name;

    @Column(nullable = false)
    Boolean del_flag;

    public Category(){
        super();
    }

    public Category(CategoryDTO dto) {
        this.id = dto.getId();
        this.category_name = dto.getCategory_name();
        this.del_flag = dto.getDel_flag();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public Boolean getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(Boolean del_flag) {
        this.del_flag = del_flag;
    }
}
