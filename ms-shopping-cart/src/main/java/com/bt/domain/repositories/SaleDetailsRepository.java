package com.bt.domain.repositories;

import com.bt.domain.entities.SaleDetailsEntity;
import com.bt.domain.entities.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleDetailsRepository extends JpaRepository<SaleDetailsEntity, Long> {
    List<SaleDetailsEntity> findBySaleEntity(final SaleEntity saleEntity);
}
