package com.shoeshop.exceptions;

import static com.shoeshop.response.FailureInfo.INTERNAL_SERVER_ERROR;
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

    @ExceptionHandler(NullPointerException.class)
    protected BaseResponse nullPointerException(NullPointerException e) {
        log.error("", e);
        return new BaseResponse(INTERNAL_SERVER_ERROR);
    }
}
