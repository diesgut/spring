package com.bank.common.exception.handler;

import com.bank.common.exception.BusinessException;
import com.bank.common.response.ApiExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUnknownHostException(Exception ex) {
        ApiExceptionResponse apiExceptionResponse = ApiExceptionResponse.builder()
                .type("TECHNICAL ERROR")
                .title("Unknown Error")
                .code("0")
                .detail(ex.getLocalizedMessage())
                .instance(ex.getClass().getName())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiExceptionResponse);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(BusinessException ex) {
        ApiExceptionResponse apiExceptionResponse = ApiExceptionResponse.builder()
                .type("BUSINESS ERROR")
                .title("BusinessException")
                .code(ex.getCode())
                .detail(ex.getLocalizedMessage())
                .instance(ex.getClass().getName())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiExceptionResponse);
    }
}
