package com.shoeshop.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SuccessInfo {
    GET_PRODUCT("GET PRODUCT"),
    CREATE_PRODUCT("CREATE PRODUCT"),
    GET_ALL_PRODUCT("GET ALL PRODUCT"),
    GET_ALL_CATEGORIES("GET ALL CATEGORIES"),
    GET_HIERARCHICAL_CATEGORIES("GET HIERARCHICAL CATEGORIES"),
    GET_PRODUCTS_BY_CATEGORY("GET PRODUCTS BY CATEGORY"),
    DELETE_PRODUCT("DELETE_PRODUCT");

    private final String code = "1";
    private final String message;
}
