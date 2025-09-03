package com.store.system.repository;

import com.store.system.entity.SaleAndInventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleAndInventoryRepository extends JpaRepository<SaleAndInventory, Long> {

    @Query("Select s from SaleAndInventory s where s.good.goodName like Lower(CONCAT('%', :keyword, '%')) "+
    "Or s.voucher.voucherNo like Lower(CONCAT('%', :keyword, '%'))")
    public Page<SaleAndInventory> getSaleAndInventoryBySearch(String keyword, Pageable pageable);
}
