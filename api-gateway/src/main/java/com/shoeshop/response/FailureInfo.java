package com.shoeshop.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum FailureInfo {

    INVALID_INPUT("98", "Invalid input"),
    INTERNAL_SERVER_ERROR("500", "Invalid input");

    private final String code;
    private final String message;
}
