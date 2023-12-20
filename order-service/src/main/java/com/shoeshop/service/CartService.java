package com.shoeshop.service;

import org.springframework.stereotype.Service;
import com.shoeshop.dto.CartCreateDto;
import com.shoeshop.dto.CartDto;
import com.shoeshop.dto.CartItemDto;
import com.shoeshop.entity.Cart;
import com.shoeshop.entity.CartItem;
import com.shoeshop.repository.CartItemRepository;
import com.shoeshop.repository.CartRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public CartDto createCart(CartCreateDto cartDto) {
        Cart cart = Cart.builder() //
                .customerId(cartDto.getCustomerId()) //
                .build();
        cart = cartRepository.save(cart);
        return CartDto.from(cart);
    }

    public CartItemDto addCartItemToCart(CartItemDto cartItemDto) {
        CartItem cartItem = CartItem.builder() //
                .cartId(cartItemDto.getCartId()) //
                .customerId(cartItemDto.getCustomerId()) //
                .productId(cartItemDto.getProductId()) //
                .quantity(cartItemDto.getQuantity()) //
                .totalPrice(cartItemDto.getTotalPrice()) //
                .build();
        cartItem = cartItemRepository.save(cartItem);
        return CartItemDto.from(cartItem);
    }
}
