package com.bt.mappers;

import com.bt.domain.entities.SaleDetailsEntity;
import com.bt.dtos.SaleDetailsRegisterDto;
import com.bt.dtos.SaleDetailsViewDto;

import java.util.List;

public interface SaleDetailsMapper {
    SaleDetailsEntity convertToEntityFromView(final SaleDetailsViewDto saleDetailsViewDto);
    List<SaleDetailsViewDto> convertToViewListFromEntityList(final List<SaleDetailsEntity> saleDetailListEntity);
    SaleDetailsViewDto convertToViewFromEntity(final SaleDetailsEntity saleDetailsEntity);
    List<SaleDetailsEntity> convertToEntityListFromRequestList(final List<SaleDetailsRegisterDto> salesDetailsRegisterDto);
    SaleDetailsEntity convertToEntityFromRequest(final SaleDetailsRegisterDto saleDetailsRegisterDto);
}
