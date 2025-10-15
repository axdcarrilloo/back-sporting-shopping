package com.bt.exceptions;

import com.bt.dtos.ResponseMainDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorCredentialsException extends RuntimeException {
    private final HttpStatus statusHttp = HttpStatus.UNAUTHORIZED;
    private final ResponseMainDto responseMainDto;

    public ErrorCredentialsException(final ResponseMainDto responseMainDto) {
        super(responseMainDto.getMessage());
        this.responseMainDto = responseMainDto;
    }
}
