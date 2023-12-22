package com.shoeshop.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class APIResponseWrapperForJsonParsing<T> {

    @JsonProperty("isSuccess")
    private boolean isSuccess;
    private String code;
    private String message;
    private T data;
}
