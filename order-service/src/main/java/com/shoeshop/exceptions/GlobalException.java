package com.shoeshop.exceptions;

import com.shoeshop.response.FailureInfo;
import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException {
    private final FailureInfo failureInfo;

    public GlobalException(FailureInfo failureInfo) {
        super(failureInfo.getMessage());
        this.failureInfo = failureInfo;
    }
}
