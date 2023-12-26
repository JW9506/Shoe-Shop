package com.shoeshop.dto;

import java.math.BigDecimal;
import com.shoeshop.entity.OrderItem;
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
public class OrderItemDto {

    private Long orderId;
    private Long productId;
    private Long quantity;
    private BigDecimal totalPrice;

    public static OrderItemDto from(OrderItem orderItem) {
        return OrderItemDto.builder() //
                .orderId(orderItem.getOrder().getId()) //
                .productId(orderItem.getProductId()) //
                .quantity(orderItem.getQuantity()) //
                .totalPrice(orderItem.getTotalPrice()) //
                .build();
    } 
}
