package com.store.system.service;

import com.store.system.dto.GoodArrivalDTO;
import com.store.system.entity.GoodArrival;
import com.store.system.repository.GoodArrivalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GoodArrivalService {

    @Autowired
    private GoodArrivalRepository goodArrivalRepository;

    public Page<GoodArrival> getArrivalList(Pageable pageable) {
        return goodArrivalRepository.findAll(pageable);
    }

    public Page<GoodArrival> getArrivalListBySearch(String keyword, Pageable pageable) {
        return goodArrivalRepository.findBySearch(keyword, pageable);
    }

    public void saveArrival(GoodArrival arrival) {
        goodArrivalRepository.save(arrival);
    }

    public void deleteArrival(Long id) {
        goodArrivalRepository.deleteById(id);
    }

    public GoodArrivalDTO getArrivalById(Long id) {
        GoodArrival goodArrival = goodArrivalRepository.getReferenceById(id);
        return new GoodArrivalDTO(goodArrival);
    }

    public List<GoodArrival> getArrivalByGoodId(Long id, LocalDate fromDate, LocalDate toDate) {
        return goodArrivalRepository.getArrivalByData(id, fromDate, toDate);
    }
}
