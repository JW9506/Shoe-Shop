package com.shoeshop.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.shoeshop.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    protected BaseResponse entityNotFoundException(EntityNotFoundException e) {
        log.error("", e);
        return new BaseResponse(e.getFailureInfo());
    }

    @ExceptionHandler(GlobalException.class)
    protected BaseResponse globalException(GlobalException e) {
        if (e.getInnerException() != null) {
            e.getInnerException().printStackTrace();
        }
        return new BaseResponse(e.getFailureInfo());
    }
}
