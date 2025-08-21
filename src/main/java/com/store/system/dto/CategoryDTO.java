package com.store.system.dto;


import com.store.system.entity.Category;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;

public class CategoryDTO {
    private Long id;

    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    @Column(name = "category_name")
    String categoryName;

    Boolean del_flag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters") String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(@Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters") String categoryName) {
        this.categoryName = categoryName;
    }

    public Boolean getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(Boolean del_flag) {
        this.del_flag = del_flag;
    }

    public CategoryDTO() {
        super();
    }

    public CategoryDTO(Category category) {
        this.categoryName = category.getCategoryName();
        this.id = category.getId();
        this.del_flag = category.getDel_flag();
    }
}
