package com.shoeshop.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BaseResponse {
    private final Boolean isSuccess;
    private final String code;
    private final String message;

    public BaseResponse(SuccessInfo successInfo) {
        this.isSuccess = true;
        this.code = successInfo.getCode();
        this.message = successInfo.getMessage();
    }

    public BaseResponse(FailureInfo failureInfo) {
        this.isSuccess = false;
        this.code = failureInfo.getCode();
        this.message = failureInfo.getMessage();
    }
}
