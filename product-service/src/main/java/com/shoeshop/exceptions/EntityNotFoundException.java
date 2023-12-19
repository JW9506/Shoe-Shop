package com.shoeshop.exceptions;

import com.shoeshop.response.FailureInfo;
import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {
    private final FailureInfo failureInfo;

    public EntityNotFoundException(FailureInfo failureInfo) {
        super(failureInfo.getMessage());
        this.failureInfo = failureInfo;
    }
}
