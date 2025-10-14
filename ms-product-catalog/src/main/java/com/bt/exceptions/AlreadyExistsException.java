package com.bt.exceptions;

import com.bt.dtos.ResponseMainDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AlreadyExistsException extends RuntimeException {
    private final HttpStatus statusHttp = HttpStatus.CONFLICT;
    private final ResponseMainDto responseMainDto;

    public AlreadyExistsException(final ResponseMainDto responseMainDto) {
        super(responseMainDto.getMessage());
        this.responseMainDto = responseMainDto;
    }
}
