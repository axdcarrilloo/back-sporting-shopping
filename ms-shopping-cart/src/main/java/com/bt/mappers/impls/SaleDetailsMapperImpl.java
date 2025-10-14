package com.bt.mappers.impls;

import com.bt.domain.entities.SaleDetailsEntity;
import com.bt.domain.entities.SaleEntity;
import com.bt.dtos.SaleDetailsRegisterDto;
import com.bt.dtos.SaleDetailsViewDto;
import com.bt.mappers.ProductMapper;
import com.bt.mappers.SaleDetailsMapper;
import com.bt.mappers.SaleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SaleDetailsMapperImpl implements SaleDetailsMapper {
    private final SaleMapper saleMapper;
    private final ProductMapper productMapper;

    @Override
    public SaleDetailsEntity convertToEntityFromView(SaleDetailsViewDto saleDetailsViewDto) {
        return SaleDetailsEntity.builder().id(saleDetailsViewDto.getId())
//                .sale(saleMapper.convertToViewFromEntity(saleDetailsEntity.getSaleEntity()))
                .cantProduct(saleDetailsViewDto.getCantProduct())
                .productEntity(productMapper.convertToEntityFromView(saleDetailsViewDto.getProduct())).build()
        ;
    }

    @Override
    public List<SaleDetailsViewDto> convertToViewListFromEntityList(List<SaleDetailsEntity> saleDetailListEntity) {
        List<SaleDetailsViewDto> saleDetailListView = new ArrayList<>();
        saleDetailListEntity.forEach(details -> saleDetailListView.add(convertToViewFromEntity(details)));
        return saleDetailListView;
    }

    @Override
    public SaleDetailsViewDto convertToViewFromEntity(SaleDetailsEntity saleDetailsEntity) {
        return SaleDetailsViewDto.builder().id(saleDetailsEntity.getId())
//                .sale(saleMapper.convertToViewFromEntity(saleDetailsEntity.getSaleEntity()))
                .cantProduct(saleDetailsEntity.getCantProduct())
                .product(productMapper.convertToViewFromEntity(saleDetailsEntity.getProductEntity())).build()
        ;
    }

    @Override
    public List<SaleDetailsEntity> convertToEntityListFromRequestList(List<SaleDetailsRegisterDto> saleDetailsListRegisterDto) {
        List<SaleDetailsEntity> saleDetailsListEntity = new ArrayList<>();
        saleDetailsListRegisterDto.forEach(details -> {
                saleDetailsListEntity.add(convertToEntityFromRequest(details));
        });
        return saleDetailsListEntity;
    }

    @Override
    public SaleDetailsEntity convertToEntityFromRequest(SaleDetailsRegisterDto saleDetailsRegisterDto) {
        return SaleDetailsEntity.builder()
                .saleEntity(saleMapper.convertToEntityFromView(saleDetailsRegisterDto.getSaleView()))
                .cantProduct(saleDetailsRegisterDto.getCantProduct())
                .productEntity(productMapper.convertToEntityFromView(saleDetailsRegisterDto.getProduct())).build()
        ;
    }
}
