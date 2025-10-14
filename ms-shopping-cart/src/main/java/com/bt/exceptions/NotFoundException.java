package com.bt.exceptions;

import com.bt.dtos.ResponseMainDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends RuntimeException {
    private final HttpStatus statusHttp = HttpStatus.NOT_FOUND;
    private final ResponseMainDto responseMainDto;

    public NotFoundException(final ResponseMainDto responseMainDto) {
        super(responseMainDto.getMessage());
        this.responseMainDto = responseMainDto;
    }
}
