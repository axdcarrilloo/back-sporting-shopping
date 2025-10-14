package com.bt.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRegisterDto {
    private String nombre;
    private Double precio;
    private String descripcion;
    private String urlImagen;
}
