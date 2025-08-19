package com.store.system.dto;


import com.store.system.entity.Category;
import jakarta.validation.constraints.Size;

public class CategoryDTO {
    private Long id;

    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    String category_name;

    Boolean del_flag;

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

    public CategoryDTO() {
        super();
    }

    public CategoryDTO(Category category) {
        this.category_name = category.getCategory_name();
        this.id = category.getId();
        this.del_flag = category.getDel_flag();
    }
}
