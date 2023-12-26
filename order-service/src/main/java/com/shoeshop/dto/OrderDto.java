package com.shoeshop.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
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
    private List<OrderItemDto> orderItemDtos;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static OrderDto from(Order order) {
        return OrderDto.builder() //
                .id(order.getId()) //
                .customer(CustomerDto.from(order.getCustomer())) //
                .orderDetails(order.getOrderDetails()) //
                .paymentStatus(order.getPaymentStatus()) //
                .orderItemDtos(order.getOrderItems().stream().map(OrderItemDto::from).collect(Collectors.toList())) //
                .createdAt(order.getCreatedAt()) //
                .updatedAt(order.getUpdatedAt()) //
                .build();
    }

}
