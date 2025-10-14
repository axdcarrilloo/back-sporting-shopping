package com.bt.mappers;

import com.bt.domain.entities.SaleEntity;
import com.bt.dtos.SaleRegisterDto;
import com.bt.dtos.SaleViewDto;

public interface SaleMapper {
    SaleViewDto convertToViewFromEntity(final SaleEntity saleEntity);
    SaleEntity convertToEntityFromView(final SaleViewDto saleViewDto);
    SaleEntity convertToEntityFromRequest(final SaleRegisterDto saleRegisterDto);
}
