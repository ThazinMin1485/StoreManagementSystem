package com.store.system.controller;

import com.store.system.entity.Good;
import com.store.system.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiContorller {
    private final GoodService goodService;

    public ApiContorller(GoodService goodService) {
        this.goodService = goodService;
    }

    @GetMapping("/good/list")
    public List<Good> getAllGoods() {
        return goodService.getList();
    }
}
