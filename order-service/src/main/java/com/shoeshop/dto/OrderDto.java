package com.shoeshop.dto;

import java.time.LocalDateTime;
import com.shoeshop.entity.Order;
import com.shoeshop.enums.PaymentStatus;
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
    private PaymentStatus paymentStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static OrderDto from(Order order) {
        return OrderDto.builder() //
                .id(order.getId()) //
                .customer(CustomerDto.from(order.getCustomer())) //
                .orderDetails(order.getOrderDetails()) //
                .paymentStatus(order.getPaymentStatus()) //
                .createdAt(order.getCreatedAt()) //
                .updatedAt(order.getUpdatedAt()) //
                .build();
    }

}
