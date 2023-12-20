package com.shoeshop.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderCreateDto {

    private Long customerId;
    private String orderDetails;
}
