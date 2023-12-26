package com.shoeshop.service;

import static com.shoeshop.response.FailureInfo.INVALID_INPUT;
import org.springframework.stereotype.Service;
import com.shoeshop.dto.CartCreateDto;
import com.shoeshop.dto.CartDto;
import com.shoeshop.dto.OrderItemDto;
import com.shoeshop.entity.Cart;
import com.shoeshop.entity.OrderItem;
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

    public OrderItemDto addProductItemToOrder(OrderItemDto cartItemDto) {
        Cart cart = cartRepository.findById(cartItemDto.getCartId()).orElseThrow(() -> new EntityNotFoundException(INVALID_INPUT));
        OrderItem cartItem = OrderItem.builder() //
                .cart(cart) //
                .productId(cartItemDto.getProductId()) //
                .quantity(cartItemDto.getQuantity()) //
                .totalPrice(cartItemDto.getTotalPrice()) //
                .build();
        cartItem = cartItemRepository.save(cartItem);
        return OrderItemDto.from(cartItem);
    }

    public CartDto getCartById(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(INVALID_INPUT));
        return CartDto.from(cart);
    }

    public OrderItemDto getCartItemById(Long id) {
        OrderItem cartItem = cartItemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(INVALID_INPUT));
        return OrderItemDto.from(cartItem);
    }
}
