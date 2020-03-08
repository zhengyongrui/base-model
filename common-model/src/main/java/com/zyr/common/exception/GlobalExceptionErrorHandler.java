package com.zyr.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zhengyongrui
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionErrorHandler {

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<GlobalExceptionErrorBody> error(SecurityException e) {
        log.warn("发生SecurityException异常", e);
        return new ResponseEntity<>(
                GlobalExceptionErrorBody.builder()
                .body(e.getMessage())
                .status(HttpStatus.UNAUTHORIZED.value())
                .build(),
            HttpStatus.UNAUTHORIZED
        );
    }
}
