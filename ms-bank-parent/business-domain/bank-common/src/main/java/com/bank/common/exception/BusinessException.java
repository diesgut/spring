package com.bank.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper=false)
public class BusinessException extends RuntimeException {
    private long id;
    private String code;
    private HttpStatus status;

    public BusinessException(long id, String code, String message, HttpStatus status) {
        super(message);
        this.id = id;
        this.code = code;
        this.status = status;
    }

    public BusinessException(String code, String message, HttpStatus status) {
        super(message);
        this.code = code;
        this.status = status;
    }

    public BusinessException(String message, Throwable cause) {
        super(message);
    }
}
