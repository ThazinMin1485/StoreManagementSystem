package com.store.system.repository;

import com.store.system.entity.GoodArrival;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodArrivalRepository extends JpaRepository<GoodArrival, Long> {

    @Query("Select g from GoodArrival g where Lower(g.good.goodName) like Lower(CONCAT('%', :keyword, '%'))")
    Page<GoodArrival> findBySearch(String keyword, Pageable pageable);
}
