package com.store.system.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "good")
public class Good {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "good_id")
    Long id;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false)
    String good_name;

    @Column(nullable = false)
    Boolean del_flag;

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
}
