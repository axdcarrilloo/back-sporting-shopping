package com.bt.mappers.impls;

import com.bt.domain.entities.SaleEntity;
import com.bt.dtos.SaleRegisterDto;
import com.bt.dtos.SaleViewDto;
import com.bt.mappers.SaleMapper;
import com.bt.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaleMapperImpl implements SaleMapper {
    private final UserMapper userMapper;

    @Override
    public SaleViewDto convertToViewFromEntity(SaleEntity saleEntity) {
        return SaleViewDto.builder().id(saleEntity.getId())
                .client(userMapper.convertToViewFromEntity(saleEntity.getClientEntity()))
                .build()
        ;
    }

    @Override
    public SaleEntity convertToEntityFromView(SaleViewDto saleViewDto) {
        return SaleEntity.builder().id(saleViewDto.getId())
                .clientEntity(userMapper.convertToEntityFromView(saleViewDto.getClient()))
                .build()
        ;
    }

    @Override
    public SaleEntity convertToEntityFromRequest(SaleRegisterDto saleRegisterDto) {
        return SaleEntity.builder().clientEntity(userMapper.convertToEntityFromView(saleRegisterDto.getClient()))
                .build()
        ;
    }
}
