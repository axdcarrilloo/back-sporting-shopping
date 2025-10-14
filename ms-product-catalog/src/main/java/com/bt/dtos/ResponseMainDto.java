package com.bt.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMainDto {
    private String message;
    private Object response;
}
