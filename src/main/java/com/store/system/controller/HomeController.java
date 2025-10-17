package com.store.system.controller;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class HomeController {

    @GetMapping("/admin")
    public String home(Model model) {
        List<Map<String, String>> menuItems = List.of(Map.of("label", "Category", "path", "admin/category"), Map.of("label", "Good", "path", "admin/good"), Map.of("label", "GoodArrival", "path", "admin/arrival"), Map.of("label", "GoodDetail", "path", "admin/goodDetail"), Map.of("label", "SaleAndInventory", "path", "admin/saleAndInventory"), Map.of("label", "Voucher", "path", "admin/voucher"));
        List<List<Map<String, String>>> menuRows = List.of(menuItems.subList(0, 3), // 00, 01, 02
                menuItems.subList(3, 5), // 10, 11
                menuItems.subList(5, 6)  // 20
        );
        model.addAttribute("menuItems", menuRows);
        return "index";
    }

    @GetMapping("/user")
    public String userHome(Model model) {
        return "userHome";
    }


    @GetMapping("/")
    public void root(Authentication authentication, HttpServletResponse response) throws IOException {
        if(authentication == null) {
            response.sendRedirect("/login");
        }
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if(roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("/admin");
        } else if(roles.contains("ROLE_USER")) {
            response.sendRedirect("/user");
        }
    }
}
