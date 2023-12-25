package com.shoeshop.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SuccessInfo {
    SUCCESS("SUCCESS");

    private final String code = "1";
    private final String message;
}
