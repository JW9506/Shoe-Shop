package com.plantshop.dto;

import com.plantshop.entity.Order;
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

    public static OrderDto from(Order order) {
        return OrderDto.builder() //
                .id(order.getId()) //
                .customer(CustomerDto.from(order.getCustomer())) //
                .orderDetails(order.getOrderDetails()) //
                .createdAt(order.getCreatedAt()) //
                .updatedAt(order.getUpdatedAt()) //
                .build();
    }

}
