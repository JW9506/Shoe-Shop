package com.shoeshop.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SuccessInfo {
    SIGN_UP("SIGN UP"),
    GET_ORDER("GET ORDER"),
    POST_ORDER("POST ORDER"),

    GET_CUSTOMER("GET CUSTOMER"),
    GET_PRODUCTS("GET PRODUCTS"),
    GET_HIERARCHICAL_CATEGORIES("GET HIERARCHICAL CATEGORIES");

    private final String code = "1";
    private final String message;
}
