package com.store.system.service;

import com.store.system.dto.CategoryDTO;
import com.store.system.entity.Category;
import com.store.system.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public List<Category> getCategoryList() {
        return repository.findAll();
    }

    public Page<Category> getCategoriesBySearch(String keyword, Pageable pageable) {
        return repository.findCategoryBySearch(keyword, pageable);
    }

    public Page<Category> getCategories(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public void saveCategory(Category category) {
        repository.save(category);
    }

    public CategoryDTO getCategoryById(Long id) {
        Category category = repository.getReferenceById(id);
        CategoryDTO categoryDTO = new CategoryDTO(category);
        return categoryDTO;
    }

    public void deleteCategoryById(Long id) {
        repository.deleteById(id);
    }

    public void updateCategory(Category category) {
        repository.save(category);
    }
}
