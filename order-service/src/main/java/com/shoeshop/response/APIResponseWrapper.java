package com.shoeshop.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class APIResponseWrapper<T> {
    private boolean isSuccess;
    private String code;
    private String message;
    private T data;
}

