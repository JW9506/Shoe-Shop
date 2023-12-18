package com.plantshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.plantshop.response.BaseResponse;
import com.plantshop.response.FailureInfo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected BaseResponse internalSeverException(Exception e){
        log.error("internal server exception.", e);
        return new BaseResponse(FailureInfo.INTERNAL_SERVER_ERROR);
    }
}
