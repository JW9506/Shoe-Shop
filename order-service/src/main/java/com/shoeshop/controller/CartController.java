package com.shoeshop.controller;

import static com.shoeshop.response.SuccessInfo.GET_CART;
import static com.shoeshop.response.SuccessInfo.POST_CART_ITEM;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shoeshop.dto.CartDto;
import com.shoeshop.dto.CartItemDto;
import com.shoeshop.response.DataResponse;
import com.shoeshop.service.CartService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
@Tag(name = "Cart", description = "Cart API")
public class CartController {

    private final CartService cartService;

    @GetMapping("/{id}")
    public DataResponse<CartDto> getCartById(@PathVariable Long id) {
        CartDto cartDto = cartService.getCartById(id);
        return new DataResponse<CartDto>(GET_CART, cartDto);
    }

    @PostMapping("/addItem")
    public DataResponse<CartItemDto> addCartItemToCart(@RequestBody CartItemDto cartItemDto) {
        log.info("Adding cart item to cart: {}", cartItemDto);
        CartItemDto cartItem = cartService.addCartItemToCart(cartItemDto);
        return new DataResponse<>(POST_CART_ITEM, cartItem);
    }
}
