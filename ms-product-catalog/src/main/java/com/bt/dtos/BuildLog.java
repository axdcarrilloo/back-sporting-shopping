package com.bt.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BuildLog {
    private String nameClass;
    private String nameMethod;
    private String message;
    private Throwable error;
}
