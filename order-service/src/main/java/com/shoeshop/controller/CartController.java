package com.shoeshop.controller;

import static com.shoeshop.response.SuccessInfo.GET_CART;
import static com.shoeshop.response.SuccessInfo.GET_CART_ITEM;
import static com.shoeshop.response.SuccessInfo.POST_CART_ITEM;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shoeshop.dto.CartDto;
import com.shoeshop.dto.OrderItemDto;
import com.shoeshop.response.DataResponse;
import com.shoeshop.service.CartService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order/add")
@Tag(name = "Cart", description = "Cart API")
public class CartController {

    private final CartService cartService;

    @GetMapping("/{id}")
    public DataResponse<CartDto> getCartById(@PathVariable Long id) {
        CartDto cartDto = cartService.getCartById(id);
        return new DataResponse<CartDto>(GET_CART, cartDto);
    }

    @GetMapping("/item/{id}")
    public DataResponse<OrderItemDto> getCartItemById(@PathVariable Long id) {
        OrderItemDto cartDto = cartService.getCartItemById(id);
        return new DataResponse<>(GET_CART_ITEM, cartDto);
    }

    @PostMapping("/addItem")
    public DataResponse<OrderItemDto> addCartItemToCart(@RequestBody OrderItemDto cartItemDto) {
        log.info("Adding cart item to cart: {}", cartItemDto);
        OrderItemDto cartItem = cartService.addCartItemToCart(cartItemDto);
        return new DataResponse<>(POST_CART_ITEM, cartItem);
    }
}
