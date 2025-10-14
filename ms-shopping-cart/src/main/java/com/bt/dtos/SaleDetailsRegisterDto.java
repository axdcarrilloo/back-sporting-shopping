package com.bt.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleDetailsRegisterDto {
    private SaleViewDto saleView;
    private Integer cantProduct;
    private ProductViewDto product;
}
