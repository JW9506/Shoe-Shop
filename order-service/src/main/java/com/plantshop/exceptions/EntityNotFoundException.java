package com.plantshop.exceptions;

import com.plantshop.response.FailureInfo;
import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {
    private final FailureInfo failureInfo;

    public EntityNotFoundException(FailureInfo failureInfo) {
        super(failureInfo.getMessage());
        this.failureInfo = failureInfo;
    }
}
