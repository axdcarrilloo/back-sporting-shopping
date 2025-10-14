package com.bt.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleViewDto {
    private Long id;
    private UserViewDto client;
    private List<SaleDetailsViewDto> saleDetails;
}
