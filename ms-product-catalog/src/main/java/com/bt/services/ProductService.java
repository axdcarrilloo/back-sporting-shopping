package com.bt.services;

import com.bt.dtos.ProductRegisterDto;
import com.bt.dtos.ResponseMainDto;

public interface ProductService {
    ResponseMainDto deleteById(final Long id);
    ResponseMainDto getAll();
    ResponseMainDto register(final ProductRegisterDto productRegisterDto);
}
