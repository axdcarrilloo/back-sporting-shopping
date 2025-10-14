package com.bt.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductViewDto {
    private Long id;
    private String nombre;
    private Double precio;
    private String descripcion;
    private String urlImagen;
}
