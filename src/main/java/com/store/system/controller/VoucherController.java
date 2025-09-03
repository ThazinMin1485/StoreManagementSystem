package com.store.system.controller;

import com.store.system.entity.Voucher;
import com.store.system.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VoucherController {

    @Autowired
    private VoucherService service;

    @GetMapping("/voucher/list")
    public String getVoucherList() {
        return "voucher/voucherList";
    }

    @GetMapping("/voucher/data")
    @ResponseBody
    public Page<Voucher> setVoucherData(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "5") int size,
                                        @RequestParam(defaultValue = "id") String sortBy,
                                        @RequestParam(defaultValue = "asc") String sortDir,
                                        @RequestParam(defaultValue = "") String keyword) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        if (keyword != null && !keyword.isEmpty()) {
            return service.getVoucherListBySearch(keyword, pageable);
        } else {
            return service.getVoucherList(pageable);
        }
    }
}
