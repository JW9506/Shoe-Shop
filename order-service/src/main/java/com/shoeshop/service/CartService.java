package com.shoeshop.service;

import static com.shoeshop.response.FailureInfo.INVALID_INPUT;
import org.springframework.stereotype.Service;
import com.shoeshop.dto.CartCreateDto;
import com.shoeshop.dto.CartDto;
import com.shoeshop.dto.CartItemDto;
import com.shoeshop.entity.Cart;
import com.shoeshop.entity.CartItem;
import com.shoeshop.exceptions.EntityNotFoundException;
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
        Cart cart = cartRepository.findById(cartItemDto.getCartId()).orElseThrow(() -> new EntityNotFoundException(INVALID_INPUT));
        CartItem cartItem = CartItem.builder() //
                .cart(cart) //
                .productId(cartItemDto.getProductId()) //
                .quantity(cartItemDto.getQuantity()) //
                .totalPrice(cartItemDto.getTotalPrice()) //
                .build();
        cartItem = cartItemRepository.save(cartItem);
        return CartItemDto.from(cartItem);
    }

    public CartDto getCartById(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(INVALID_INPUT));
        return CartDto.from(cart);
    }

    public CartItemDto getCartItemById(Long id) {
        CartItem cartItem = cartItemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(INVALID_INPUT));
        return CartItemDto.from(cartItem);
    }
}
