package com.store.system.repository;

import com.store.system.entity.GoodDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailRepository extends JpaRepository<GoodDetail, Long> {

}
