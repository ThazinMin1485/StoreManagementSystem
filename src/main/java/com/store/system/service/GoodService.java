package com.store.system.service;

import com.store.system.dto.GoodDTO;
import com.store.system.entity.Good;
import com.store.system.repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodService {

    @Autowired
    private GoodRepository goodRepository;

    public Page<Good> getGoodList(Pageable pageable) {
        return goodRepository.findAll(pageable);
    }

    public void createGood(Good good) {
        goodRepository.save(good);
    }

    public void saveGood(Good good) {
        goodRepository.save(good);
    }

    public void deleteGood(Long id) {
        goodRepository.deleteById(id);
    }

    public GoodDTO getGoodById(Long id) {
        Good good =  goodRepository.getReferenceById(id);
        GoodDTO goodDTO= new GoodDTO(good);
        return goodDTO;
    }
}
