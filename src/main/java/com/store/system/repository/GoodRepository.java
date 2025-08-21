package com.store.system.repository;

import com.store.system.entity.Good;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodRepository extends JpaRepository<Good, Long> {

    Page<Good> findAll(Pageable pageable);

    @Query("Select g from Good g where LOWER(g.goodName) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
            "   OR CAST(g.kg AS string) LIKE CONCAT('%', :keyword, '%') " +
            "   OR CAST(g.quantity AS string) LIKE CONCAT('%', :keyword, '%')" +
    "Or LOWER(g.category.categoryName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Good> findByKeyword(String keyword, Pageable pageable);
}
