package com.store.system.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        List<Map<String, String>> menuItems = List.of(Map.of("label", "Category", "path", "category"), Map.of("label", "Good", "path", "good"), Map.of("label", "GoodArrival", "path", "arrival"), Map.of("label", "GoodDetail", "path", "goodDetail"), Map.of("label", "SaleAndInventory", "path", "saleAndInventory"), Map.of("label", "Voucher", "path", "voucher"));
        List<List<Map<String, String>>> menuRows = List.of(menuItems.subList(0, 3), // 00, 01, 02
                menuItems.subList(3, 5), // 10, 11
                menuItems.subList(5, 6)  // 20
        );
        model.addAttribute("menuItems", menuRows);
        return "index";
    }
}
