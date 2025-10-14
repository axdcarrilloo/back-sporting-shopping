package com.bt.services;

import com.bt.dtos.ResponseMainDto;
import com.bt.dtos.SaleRegisterDto;

public interface SaleService {
    ResponseMainDto getAll();
    ResponseMainDto register(final SaleRegisterDto saleRegisterDto);
}
