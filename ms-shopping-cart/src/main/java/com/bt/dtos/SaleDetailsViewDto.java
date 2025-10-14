package com.bt.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleDetailsViewDto {
    private Long id;
//    private SaleViewDto sale;
    private Integer cantProduct;
    private ProductViewDto product;
}
