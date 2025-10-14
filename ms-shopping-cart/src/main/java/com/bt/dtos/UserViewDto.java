package com.bt.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserViewDto {
    private Long id;

    private String nombres;

    private String apellidos;

    private String direccionEnvios;

    private String email;

    private LocalDate fechaNacimiento;

    private String password;
}
