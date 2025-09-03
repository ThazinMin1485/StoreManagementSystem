package com.store.system.service;

import com.store.system.dto.GoodDetailDTO;
import com.store.system.entity.GoodDetail;
import com.store.system.repository.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DetailService {
    @Autowired
    private DetailRepository detailRepository;

    public Page<GoodDetail> getDetailList(Pageable pageable) {
        return detailRepository.findAll(pageable);
    }

    public void saveData(GoodDetail detail) {
        detailRepository.save(detail);
    }

    public GoodDetailDTO getGoodDetail(Long id) {
        GoodDetail detail = detailRepository.getReferenceById(id);
        return new GoodDetailDTO(detail);
    }

}
