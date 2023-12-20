package com.shoeshop.dto;

import java.util.ArrayList;
import java.util.List;
import com.shoeshop.entity.Cart;
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
public class CartDto {

    private Long id;
    private Long customerId;
    private List<CartItemDto> cartItems;

    public static CartDto from(Cart cart) {
        return CartDto.builder() //
                .id(cart.getId()) //
                .customerId(cart.getCustomerId()) //
                .cartItems(cart.getCartItems() == null ? new ArrayList<>() : cart.getCartItems().stream()
                        .map(CartItemDto::from).toList()) //
                .build();
    }
}
