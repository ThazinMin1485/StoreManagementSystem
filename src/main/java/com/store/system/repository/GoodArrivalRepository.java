package com.store.system.repository;

import com.store.system.entity.GoodArrival;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GoodArrivalRepository extends JpaRepository<GoodArrival, Long> {

    @Query("Select g from GoodArrival g where Lower(g.good.goodName) like Lower(CONCAT('%', :keyword, '%'))")
    Page<GoodArrival> findBySearch(String keyword, Pageable pageable);

    @Query("Select g from GoodArrival g where g.good.id = :id and g.arrivalTime between :fromDate and :toDate")
    List<GoodArrival> getArrivalByData(Long id, LocalDate fromDate, LocalDate toDate);
}
