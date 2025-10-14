package com.bt.mappers.impls;

import com.bt.domain.entities.ProductEntity;
import com.bt.dtos.ProductRegisterDto;
import com.bt.dtos.ProductViewDto;
import com.bt.mappers.ProductMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapperImpl implements ProductMapper {
    @Override
    public List<ProductViewDto> convertToViewListFromEntityList(List<ProductEntity> productsEntity) {
        List<ProductViewDto> productsView = new ArrayList<>();
        productsEntity.forEach(productEntity -> productsView.add(convertToViewFromEntity(productEntity)));
        return productsView;
    }

    @Override
    public ProductViewDto convertToViewFromEntity(ProductEntity productEntity) {
        return ProductViewDto.builder().id(productEntity.getId()).nombre(productEntity.getNombre())
                .precio(productEntity.getPrecio()).descripcion(productEntity.getDescripcion())
                .urlImagen(productEntity.getUrlImagen()).build()
        ;
    }

    @Override
    public ProductEntity convertToEntityFromRequest(ProductRegisterDto productRegisterDto) {
        return ProductEntity.builder().nombre(productRegisterDto.getNombre())
                .precio(productRegisterDto.getPrecio()).descripcion(productRegisterDto.getDescripcion())
                .urlImagen(productRegisterDto.getUrlImagen()).build()
        ;
    }
}
