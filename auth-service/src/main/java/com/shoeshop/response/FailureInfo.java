package com.shoeshop.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum FailureInfo {

    INVALID_INPUT("98", "INVALID_INPUT"),
    INTERNAL_SERVER_ERROR("500", "INTERNAL_SERVER_ERROR");

    private final String code;
    private final String message;
}
