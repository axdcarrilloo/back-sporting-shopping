package com.bt.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleRegisterDto {
    private UserViewDto client;
    private List<SaleDetailsRegisterDto> saleDetails;
}
