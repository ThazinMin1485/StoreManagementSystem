package com.store.system.controller;

import com.store.system.dto.CategoryDTO;
import com.store.system.entity.Category;
import com.store.system.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CategoryContorller {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category/list")
    public String categoryList(Model model) {
        return "category/category-list";
    }

    @GetMapping("/category/data")
    @ResponseBody
    public Page<Category> categoriesData(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "5") int size,
                                         @RequestParam(defaultValue = "id") String sortBy,
                                         @RequestParam(defaultValue = "asc") String sortDir,
                                         @RequestParam(defaultValue = "") String keyword) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        if(keyword != null && !keyword.isEmpty()) {
            return categoryService.getCategoriesBySearch(keyword, pageable);
        } else {
            return categoryService.getCategories(pageable); // returns Page<Good> as JSON
        }
    }

    @GetMapping("/category/create")
    public String createCategory(Model model) {
        CategoryDTO category = new CategoryDTO();
        model.addAttribute("category", category);
        return "category/createCategory";
    }

    @PostMapping("/category/create")
    public String categoryCreate(@Valid @ModelAttribute("category") CategoryDTO categoryDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("category", categoryDTO);
            return "category/createCategory";
        }
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setDel_flag(false);
        categoryService.saveCategory(category);
        return "redirect:/category/list";
    }

    @GetMapping("/category/edit/{id}")
    public String goEditCategory(@PathVariable Long id, Model model) {
        CategoryDTO category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "category/editCategory";
    }

    @PostMapping("/category/update")
    public String updateCategory(@Valid @ModelAttribute("category") CategoryDTO category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("category", category);
            return "category/editCategory";
        }
        Category catego = new Category(category);
        categoryService.updateCategory(catego);
        return "redirect:/category/list";
    }

    @GetMapping("/category/delete/{id}")
    public String deleteCategory(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            categoryService.deleteCategoryById(id);
            redirectAttributes.addFlashAttribute("success", "Category deleted successfully!");
        } catch(DataIntegrityViolationException e){
            redirectAttributes.addFlashAttribute("error", "Category is used and cannot be deleted!");
        }
        return "redirect:/category/list";
    }
}
