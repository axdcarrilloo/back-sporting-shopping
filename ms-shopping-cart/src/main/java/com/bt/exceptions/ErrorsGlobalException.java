package com.bt.exceptions;

import com.bt.dtos.ResponseMainDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorsGlobalException {
    /*@ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ResponseMainDto> alreadyExistsException(final AlreadyExistsException alreadyExistsException) {
        return new ResponseEntity<>(alreadyExistsException.getResponseMainDto(), alreadyExistsException.getStatusHttp());
    }*/
    @ExceptionHandler(UnknownException.class)
    public ResponseEntity<ResponseMainDto> unknownException(final UnknownException unknownException) {
        return new ResponseEntity<>(unknownException.getResponseMainDto(), unknownException.getStatusHttp());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseMainDto> genericException(final Exception exception) {
        return new ResponseEntity<>(ResponseMainDto.builder().message("Error Desconocido")
                .response(exception.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR)
        ;
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseMainDto> notFoundException(final NotFoundException notFoundException) {
        return new ResponseEntity<>(notFoundException.getResponseMainDto(), notFoundException.getStatusHttp());
    }
}
