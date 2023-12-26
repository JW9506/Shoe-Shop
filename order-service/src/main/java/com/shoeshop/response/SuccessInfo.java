package com.shoeshop.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SuccessInfo {
    SUCCESS("SUCCESS"),
    SIGN_UP("SIGN UP"),
    GET_ORDER("GET ORDER"),
    POST_ORDER("POST ORDER"),

    GET_CUSTOMER("GET CUSTOMER"),
    GET_PRODUCTS("GET PRODUCTS"),
    GET_HIERARCHICAL_CATEGORIES("GET HIERARCHICAL CATEGORIES"),
    POST_CART_ITEM("POST CART ITEM"),
    GET_CART("GET CART"),
    GET_CART_ITEM("GET CART ITEM"),

    CHECKOUT_SUCCESSFUL("CHECKOUT_SUCCESSFUL");

    private final String code = "1";
    private final String message;
}
