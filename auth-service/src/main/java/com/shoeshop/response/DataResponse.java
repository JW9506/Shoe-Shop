package com.shoeshop.response;

import lombok.Getter;

@Getter
public class DataResponse<T> extends BaseResponse {
    private final T data;

    public DataResponse(SuccessInfo successInfo, T data) {
        super(successInfo);
        this.data = data;
    }
}

