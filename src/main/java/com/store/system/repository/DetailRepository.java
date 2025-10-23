package com.store.system.repository;

import com.store.system.entity.GoodDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailRepository extends JpaRepository<GoodDetail, Long> {

    @Query("select g from GoodDetail g where LOWER(g.good.goodName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
    "LOWER(CAST(g.percent AS string)) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
    "LOWER(CAST(g.sellingPrice AS string)) LIKE LOWER(CONCAT('%', :keyword, '%'))")
Page<GoodDetail> findDetailByKeyword(String keyword, Pageable pageable);
}
