package com.store.system.repository;

import com.store.system.entity.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {

    @Query("Select v from Voucher v where v.voucherNo like lower(CONCAT('%', :keyword, '%'))")
    public Page<Voucher> getVoucherBySearch(String keyword, Pageable pageable);
}
