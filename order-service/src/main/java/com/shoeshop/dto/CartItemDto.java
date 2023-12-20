package com.shoeshop.dto;

import java.math.BigDecimal;
import com.shoeshop.entity.CartItem;
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
public class CartItemDto {

    private Long cartId;
    private Long customerId;
    private Long productId;
    private Long quantity;
    private BigDecimal totalPrice;

    public static CartItemDto from(CartItem cartItem) {
        return CartItemDto.builder() //
                .cartId(cartItem.getCartId()) //
                .customerId(cartItem.getCustomerId()) //
                .productId(cartItem.getProductId()) //
                .quantity(cartItem.getQuantity()) //
                .totalPrice(cartItem.getTotalPrice()) //
                .build();
    } 
}
