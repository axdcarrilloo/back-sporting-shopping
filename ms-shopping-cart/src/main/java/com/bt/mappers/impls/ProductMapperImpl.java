package com.bt.mappers.impls;

import com.bt.domain.entities.ProductEntity;
import com.bt.dtos.ProductViewDto;
import com.bt.mappers.ProductMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapperImpl implements ProductMapper {
    @Override
    public ProductViewDto convertToViewFromEntity(ProductEntity productEntity) {
        return ProductViewDto.builder().id(productEntity.getId())
                .nombre(productEntity.getNombre()).precio(productEntity.getPrecio())
                .descripcion(productEntity.getDescripcion()).urlImagen(productEntity.getUrlImagen()).build()
        ;
    }

    @Override
    public ProductEntity convertToEntityFromView(ProductViewDto productViewDto) {
        return ProductEntity.builder().id(productViewDto.getId())
                .nombre(productViewDto.getNombre()).precio(productViewDto.getPrecio())
                .descripcion(productViewDto.getDescripcion()).urlImagen(productViewDto.getUrlImagen()).build()
        ;
    }
}
