package com.bt.mappers;

import com.bt.domain.entities.ProductEntity;
import com.bt.dtos.ProductViewDto;

public interface ProductMapper {
    ProductViewDto convertToViewFromEntity(final ProductEntity productEntity);
    ProductEntity convertToEntityFromView(final ProductViewDto productViewDto);
}
