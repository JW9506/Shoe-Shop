package com.plantshop.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SuccessInfo {
    SIGN_UP("SIGN UP"),
    GET_ORDER("GET ORDER"),
    POST_ORDER("POST ORDER");

    private final String code = "1";
    private final String message;
}
