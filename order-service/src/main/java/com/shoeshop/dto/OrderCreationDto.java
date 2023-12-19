package com.shoeshop.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderCreationDto {

    private Long customerId;
    private String orderDetails;
}
