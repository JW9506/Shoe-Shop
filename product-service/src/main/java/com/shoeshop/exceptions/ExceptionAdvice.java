package com.shoeshop.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.shoeshop.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    protected BaseResponse globalException(EntityNotFoundException e) {
        log.error("", e);
        return new BaseResponse(e.getFailureInfo());
    }
}
