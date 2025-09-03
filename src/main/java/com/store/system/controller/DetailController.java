package com.store.system.controller;

import com.store.system.dto.GoodArrivalDTO;
import com.store.system.dto.GoodDetailDTO;
import com.store.system.entity.Good;
import com.store.system.entity.GoodArrival;
import com.store.system.entity.GoodDetail;
import com.store.system.service.DetailService;
import com.store.system.service.GoodArrivalService;
import com.store.system.service.GoodService;
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

import java.time.LocalDate;
import java.util.List;

@Controller
public class DetailController {

    @Autowired
    private DetailService service;

    @Autowired
    private GoodService goodService;

    @Autowired
    private GoodArrivalService arrivalService;

    @GetMapping("/goodDetail/list")
    public String showList() {
        return "detail/detailList";
    }

    @GetMapping("/goodDetail/data")
    @ResponseBody
    public Page<GoodDetail> setGoodDetailLis(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "5") int size,
                                             @RequestParam(defaultValue = "id") String sortBy,
                                             @RequestParam(defaultValue = "asc") String sortDir,
                                             @RequestParam(defaultValue = "") String keyword) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
//        if(keyword != null && !keyword.isEmpty()) {
//            return service.getDetailBySearch(keyword, pageable);
//        } else {
            return service.getDetailList(pageable);
//        }

    }

    @GetMapping("/goodDetail/create")
    public String showCreatePage(Model model) {
        GoodDetailDTO detail = new GoodDetailDTO();
        List<Good> goodList = goodService.getList();
        model.addAttribute("goodDetail", detail);
        model.addAttribute("good", goodList);
        return "detail/createSalePrice";
    }

    @PostMapping("/goodDetail/create")
    public String createDetail(@ModelAttribute("goodDetail") GoodDetailDTO dto, Model model) {
        GoodDetail detail = new GoodDetail(dto);
        service.saveData(detail);
        return "redirect:/goodDetail/list";
    }

    @GetMapping("/goodDetail/arrival")
    @ResponseBody
    public Double getSellingPriceData(@RequestParam Long goodId, @RequestParam(defaultValue = "0") Long quantity, @RequestParam(defaultValue = "0") Double g,
            @RequestParam Double percent, @RequestParam LocalDate fromDate, @RequestParam LocalDate toDate) {
        List<GoodArrival> arrivalList = arrivalService.getArrivalByGoodId(goodId, fromDate, toDate);
        Double totalCost = arrivalList.stream().mapToDouble(a-> (a.getPurchasePrice() + a.getTransportationCost() + (a.getOverhead() != null ? a.getOverhead() : 0.0))).sum();
        Double kg = arrivalList.stream().mapToDouble(a-> a.getKg() != null ? a.getKg() : 0).sum();
        Long qan = arrivalList.stream().mapToLong(a-> a.getQuantity() != null ? a.getQuantity() : 0).sum();
        System.out.println("totalCost"+totalCost + "," + percent);
        System.out.println(qan);
        return calculateSellingPrice(totalCost, kg, qan, quantity, g, percent);
    }

    @GetMapping("/goodDetail/edit/{id}")
    public String goToEditPage(Model model, @PathVariable Long id) {
        GoodDetailDTO dto = service.getGoodDetail(id);
        List<Good> goodList = goodService.getList();
        model.addAttribute("goodDetail", dto);
        model.addAttribute("good", goodList);
        return "detail/editDetail";
    }

    public String updateGoodDetail(@Valid @ModelAttribute("goodDetail")GoodDetailDTO dto, BindingResult result, Model model) {
        if(result.hasErrors()) {
            List<Good> goodList = goodService.getList();
            model.addAttribute("goodDetail", dto);
            model.addAttribute("good", goodList);
            return "detail/editDetail";
        }
        service.saveData(new GoodDetail(dto));
        return "redirect:/goodDetail/list";
    }

    public Double calculateSellingPrice(Double totalCost, Double kg, Long qan, Long quantity, Double g, Double percent) {
        boolean isG = g !=0;
        System.out.println(isG);
        Double totalg = isG ? kg * 1000 : 0;
        Double averageCost = isG ? (totalCost / totalg) : (totalCost / qan) ;
        Double averageSale = averageCost + averageCost * (percent/100);
        return isG ? averageSale * g : averageSale * quantity;
    }
}
