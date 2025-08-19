package com.store.system.controller;

import com.store.system.dto.CategoryDTO;
import com.store.system.dto.GoodDTO;
import com.store.system.entity.Category;
import com.store.system.entity.Good;
import com.store.system.service.CategoryService;
import com.store.system.service.GoodService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GoodService goodService;

    @GetMapping("/")
    public String home(Model model) {
        List<Map<String, String>> menuItems = List.of(Map.of("label", "Category", "path", "category"), Map.of("label", "Good", "path", "good"), Map.of("label", "GoodArrival", "path", "goodArrival"), Map.of("label", "GoodDetail", "path", "goodDetail"), Map.of("label", "SaleAndInventory", "path", "saleAndInventory"), Map.of("label", "Voucher", "path", "voucher"));
        List<List<Map<String, String>>> menuRows = List.of(menuItems.subList(0, 3), // 00, 01, 02
                menuItems.subList(3, 5), // 10, 11
                menuItems.subList(5, 6)  // 20
        );
        model.addAttribute("menuItems", menuRows);
        return "index";
    }

    @GetMapping("/category/list")
    public String categoryList(Model model) {
        List<Category> list = categoryService.getCategoryList();
        model.addAttribute("categoryList", list);
        return "category/category-list";
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
        category.setCategory_name(categoryDTO.getCategory_name());
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
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return "redirect:/category/list";
    }

    @GetMapping("/good/list")
    public String showGoodList() {
        return "good/goodList"; // just load the page, no data here
    }
    @GetMapping("/good/data")
    @ResponseBody
    public Page<Good> getGoodsData(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return goodService.getGoodList(pageable); // returns Page<Good> as JSON
    }

    @GetMapping("/good/create")
    public String goCreateGood(Model model) {
        GoodDTO good = new GoodDTO();
        List<Category> categories = categoryService.getCategoryList();
        model.addAttribute("good", good);
        model.addAttribute("categories", categories);
        return "good/createGood";
    }

    @PostMapping("/good/create")
    public String createGood(@Valid @ModelAttribute("good") GoodDTO goodDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("good", goodDTO);
            model.addAttribute("categories", categoryService.getCategoryList());
            return "good/createGood";
        }
        Good good = new Good(goodDTO);
        goodService.saveGood(good);
        return "redirect:/good/list";
    }

    @GetMapping("/good/delete/{id}")
    public String deleteGood(@PathVariable Long id) {
        goodService.deleteGood(id);
        return "redirect:/good/list";
    }

    @GetMapping("/good/edit/{id}")
    public String editGoodPage(@PathVariable Long id, Model model) {
        GoodDTO dto = goodService.getGoodById(id);
        model.addAttribute("good", dto);
        List<Category> categories = categoryService.getCategoryList();
        model.addAttribute("categories", categories);
        return "good/editGood";
    }

    @PostMapping("/good/edit")
    public String editGood(@Valid @ModelAttribute("good") GoodDTO goodDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("good", goodDTO);
            model.addAttribute("categories", categoryService.getCategoryList());
            return "good/editGood";
        }
        Good good = new Good(goodDTO);
        goodService.saveGood(good);
        return "redirect:/good/list";
    }
}
