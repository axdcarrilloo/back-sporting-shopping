package com.bt.exceptions;

import com.bt.dtos.ResponseMainDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UnknownException extends RuntimeException {
    private final HttpStatus statusHttp = HttpStatus.INTERNAL_SERVER_ERROR;
    private final ResponseMainDto responseMainDto;

    public UnknownException(final ResponseMainDto responseMainDto) {
        super(responseMainDto.getMessage());
        this.responseMainDto = responseMainDto;
    }
}
