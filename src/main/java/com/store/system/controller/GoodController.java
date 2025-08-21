package com.store.system.controller;

import com.store.system.dto.GoodDTO;
import com.store.system.entity.Category;
import com.store.system.entity.Good;
import com.store.system.service.CategoryService;
import com.store.system.service.CommonService;
import com.store.system.service.GoodService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class GoodController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GoodService goodService;

    @Autowired
    private CommonService commonService;

    @GetMapping("/good/list")
    public String showGoodList() {
        return "good/goodList"; // just load the page, no data here
    }
    @GetMapping("/good/data")
    @ResponseBody
    public Page<Good> getGoodsData(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(defaultValue = "") String keyword) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        System.out.println(keyword);
        if(keyword != null && !keyword.isEmpty()) {
            return goodService.getGoodListBySearch(keyword, pageable);
        } else {
            return goodService.getGoodList(pageable); // returns Page<Good> as JSON
        }
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

    @GetMapping("/good/download/pdf")
    public ResponseEntity<byte[]> downloadPdf(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "5") int size,
                                              @RequestParam(defaultValue = "id") String sortBy,
                                              @RequestParam(defaultValue = "asc") String sortDir,
                                              @RequestParam(defaultValue = "") String keyword) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Good> goodPage;
        if (keyword != null && !keyword.isEmpty()) {
            goodPage = goodService.getGoodListBySearch(keyword, pageable);
        } else {
            goodPage = goodService.getGoodList(pageable); // returns Page<Good> as JSON
        }
        String[] headers = {"No", "Name", "Quantity", "Kg", "Category"};
        List<Good> goodList = goodPage.getContent();
        int index = page * size + 1;
        return commonService.addPdfData(
                goodList,
                headers,
                good -> new ArrayList<>(Arrays.asList(
                        good.getGoodName(),
                        good.getQuantity() == null ? "" : String.valueOf(good.getQuantity()),
                        good.getKg() == null ? "" : String.valueOf(good.getKg()),
                        good.getCategory().getCategoryName()
                )),
                index, new float[]{1, 2, 2, 1, 2}
        );
    }

    @GetMapping("/good/download/excel")
    public void downloadExcel(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "5") int size,
                              @RequestParam(defaultValue = "id") String sortBy,
                              @RequestParam(defaultValue = "asc") String sortDir, @RequestParam(defaultValue = "") String keyword, HttpServletResponse response) throws IOException {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Good> goodPage;
        if (keyword != null && !keyword.isEmpty()) {
            goodPage = goodService.getGoodListBySearch(keyword, pageable);
        } else {
            goodPage = goodService.getGoodList(pageable); // returns Page<Good> as JSON
        }
        List<Good> goodList = goodPage.getContent();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=goods.xlsx");
        int index = page * size + 1;
        String[] headers = {"No","Name", "Quantity", "Kg", "Category"};
        commonService.writeExcelData(goodList,headers,good -> Arrays.asList(
                good.getGoodName(),
                good.getQuantity() == null ? "" : String.valueOf(good.getQuantity()),
                good.getKg() == null ? "" : String.valueOf(good.getKg()),
                good.getCategory().getCategoryName()
        ),index,new int[] {10,20,20,10,20}, page,response, "GoodList");
    }
}
