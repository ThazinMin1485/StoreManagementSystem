package com.store.system.service;

import com.store.system.entity.SaleAndInventory;
import com.store.system.repository.SaleAndInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SaleAndInventoryService {

    @Autowired
    private SaleAndInventoryRepository repository;

    public Page<SaleAndInventory> getInventoryList(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<SaleAndInventory> getInventoryListBySearch(String keyword, Pageable pageable) {
        return repository.getSaleAndInventoryBySearch(keyword, pageable);
    }
}
