package com.bt.services;

import com.bt.dtos.ResponseMainDto;
import com.bt.dtos.SaleDetailsRegisterDto;
import com.bt.dtos.SaleDetailsViewDto;
import com.bt.dtos.SaleViewDto;

import java.util.List;

public interface SaleDetailsService {
    List<SaleDetailsViewDto> getBySale(final SaleViewDto saleView);
    ResponseMainDto register(final List<SaleDetailsRegisterDto> saleDetailsRegisterDto);
}
