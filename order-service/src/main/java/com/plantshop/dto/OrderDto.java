package com.plantshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private String orderDetails;
    private CustomerDto customer;
    private String createdAt;
    private String updatedAt;
}
