package com.store.system.controller;

import com.store.system.dto.GoodArrivalDTO;
import com.store.system.dto.GoodDTO;
import com.store.system.entity.Good;
import com.store.system.entity.GoodArrival;
import com.store.system.service.CommonService;
import com.store.system.service.GoodArrivalService;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ArrivalController {

    @Autowired
    private GoodArrivalService service;

    @Autowired
    private GoodService goodService;

    @Autowired
    private CommonService commonService;

    @GetMapping("/arrival/list")
    public String showArrivalList() {
        return "arrival/arrivalList";
    }

    @GetMapping("/arrival/data")
    @ResponseBody
    public Page<GoodArrival> setArrivalBody(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "asc") String sortDir, @RequestParam(defaultValue = "") String keyword) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        if (keyword != null && !keyword.isEmpty()) {
            return service.getArrivalListBySearch(keyword, pageable);
        } else {
            return service.getArrivalList(pageable);
        }

    }

    @GetMapping("/arrival/create")
    public String goCreate(Model model) {
        GoodArrivalDTO dto = new GoodArrivalDTO();
        model.addAttribute("arrival", dto);
        model.addAttribute("good", goodService.getList());
        return "arrival/createArrival";
    }

    @PostMapping("/arrival/create")
    public String createArrival(@Valid @ModelAttribute("arrival") GoodArrivalDTO dto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("arrival", dto);
            model.addAttribute("good", goodService.getList());
            return "arrival/createArrival";
        } else {
            GoodArrival arrival = new GoodArrival(dto);
            GoodDTO good = goodService.getGoodById(dto.getGood().getId());
            if (dto.getQuantity() != null) {
                good.setQuantity(good.getQuantity() == null ? dto.getQuantity() : good.getQuantity() + dto.getQuantity());
            }
            if (dto.getKg() != null) {
                good.setKg(good.getKg() == null ? dto.getKg() : good.getKg() + dto.getKg());

            }
            goodService.saveGood(new Good(good));
            service.saveArrival(arrival);
            return "redirect:/arrival/list";
        }
    }

//    @GetMapping("/arrival/delete/{id}")
//    public String deleteArrival(@PathVariable Long id, Model model) {
//        service.deleteArrival(id);
//        return "redirect:/arrival/list";
//    }

    @GetMapping("/arrival/edit/{id}")
    public String goEdit(@PathVariable Long id, Model model) {
        GoodArrivalDTO dto = service.getArrivalById(id);
        model.addAttribute("arrival", dto);
        model.addAttribute("good", goodService.getList());
        return "arrival/editArrival";
    }

    @PostMapping("/arrival/edit")
    public String updateArrival(@Valid @ModelAttribute("arrival") GoodArrivalDTO dto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("arrival", dto);
            model.addAttribute("good", goodService.getList());
            return "arrival/editArrival";
        }
        updateArrival(dto);
        return "redirect:/arrival/list";
    }

    @GetMapping("/arrival/download/pdf")
    public ResponseEntity<byte[]> downloadPdf(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "asc") String sortDir, @RequestParam(defaultValue = "") String keyword) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<GoodArrival> goodArrivals;
        if (keyword != null && !keyword.isEmpty()) {
            goodArrivals = service.getArrivalListBySearch(keyword, pageable);
        } else {
            goodArrivals = service.getArrivalList(pageable);
        }
        String[] headers = {"No", "Good Name", "Quantity", "Kg", "Purchase Price", "Transportation Cost", "Overhead", "Arrival Time"};
        List<GoodArrival> arrivalList = goodArrivals.getContent();
        int index = page * size + 1;
        float[] numbers = new float[]{1, 3, 2, 1, 3, 4, 2, 3};
        return commonService.addPdfData(arrivalList, headers, arrival -> new ArrayList<>(Arrays.asList(arrival.getGood().getGoodName(), arrival.getQuantity() == null ? "" : String.valueOf(arrival.getQuantity()), arrival.getKg() == null ? "" : String.valueOf(arrival.getKg()), arrival.getPurchasePrice() == null ? "" : String.valueOf(arrival.getPurchasePrice()), arrival.getTransportationCost() == null ? "" : String.valueOf(arrival.getTransportationCost()), arrival.getOverhead() == null ? "" : String.valueOf(arrival.getOverhead()), arrival.getArrivalTime().toString())), index, numbers);
    }

    @GetMapping("/arrival/download/excel")
    public void downloadExcel(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "asc") String sortDir, @RequestParam(defaultValue = "") String keyword, HttpServletResponse response) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<GoodArrival> goodArrivals;
        if (keyword != null && !keyword.isEmpty()) {
            goodArrivals = service.getArrivalListBySearch(keyword, pageable);
        } else {
            goodArrivals = service.getArrivalList(pageable);
        }
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=goods.xlsx");
        String[] headers = {"No", "Good Name", "Quantity", "Kg", "Purchase Price", "Transportation Cost", "Overhead", "Arrival Time"};
        List<GoodArrival> arrivalList = goodArrivals.getContent();
        int index = page * size + 1;
        int[] numbers = new int[]{10, 20, 20, 10, 30, 40, 20, 30};
        commonService.writeExcelData(arrivalList, headers, arrival -> Arrays.asList(arrival.getGood().getGoodName(), arrival.getQuantity() == null ? "" : String.valueOf(arrival.getQuantity()), arrival.getKg() == null ? "" : String.valueOf(arrival.getKg()), arrival.getPurchasePrice() == null ? "" : String.valueOf(arrival.getPurchasePrice()), arrival.getTransportationCost() == null ? "" : String.valueOf(arrival.getTransportationCost()), arrival.getOverhead() == null ? "" : String.valueOf(arrival.getOverhead()), arrival.getArrivalTime().toString()), index, numbers, page, response, "ArrivalList");
    }

    public void updateArrival(GoodArrivalDTO dto) {
        GoodArrival arrival = new GoodArrival(dto);
        GoodDTO good = goodService.getGoodById(dto.getGood().getId());
        GoodArrivalDTO arrivalDTO = service.getArrivalById(dto.getId());
        if (dto.getQuantity() != null || arrivalDTO.getQuantity() !=null) {
            Long changedQuantity = arrivalDTO.getQuantity() != null ? dto.getQuantity()!= null ? dto.getQuantity() - arrivalDTO.getQuantity() : -arrivalDTO.getQuantity() : dto.getQuantity();
            good.setQuantity(good.getQuantity() == null || good.getQuantity() == 0 ? changedQuantity : good.getQuantity() + changedQuantity);
        }
        if (dto.getKg() != null || arrivalDTO.getKg() != null) {
            Double changedKg = arrivalDTO.getKg() != null ? dto.getKg() !=null ? dto.getKg() - arrivalDTO.getKg() : -arrivalDTO.getKg() : dto.getKg();
            good.setKg(good.getKg() == null || good.getKg() == 0.0 ? changedKg : good.getKg() + changedKg);
        }
        goodService.saveGood(new Good(good));
        service.saveArrival(arrival);
    }

}

