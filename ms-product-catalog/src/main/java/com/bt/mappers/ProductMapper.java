package com.bt.mappers;

import com.bt.domain.entities.ProductEntity;
import com.bt.dtos.ProductRegisterDto;
import com.bt.dtos.ProductViewDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper {
    List<ProductViewDto> convertToViewListFromEntityList(final List<ProductEntity> productsEntity);
    ProductViewDto convertToViewFromEntity(final ProductEntity productEntity);
    ProductEntity convertToEntityFromRequest(final ProductRegisterDto productRegisterDto);
}
