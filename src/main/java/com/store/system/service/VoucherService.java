package com.store.system.service;

import com.store.system.entity.Voucher;
import com.store.system.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VoucherService {

    @Autowired
    private VoucherRepository repository;

    public Page<Voucher> getVoucherList(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Voucher> getVoucherListBySearch(String keyword, Pageable pageable) {
        return repository.getVoucherBySearch(keyword, pageable);
    }
}
