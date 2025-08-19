package com.store.system.repository;

import com.store.system.entity.Good;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodRepository extends JpaRepository<Good, Long> {

    Page<Good> findAll(Pageable pageable);

}
