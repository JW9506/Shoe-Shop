package com.shoeshop.controller;

import static com.shoeshop.response.FailureInfo.CHECKOUT_UNSUCCESSFUL;
import static com.shoeshop.response.FailureInfo.INTERNAL_SERVER_ERROR;
import static com.shoeshop.response.FailureInfo.INVALID_INPUT;
import static com.shoeshop.response.SuccessInfo.CHECKOUT_SUCCESSFUL;
import static com.shoeshop.response.SuccessInfo.GET_ORDER;
import static com.shoeshop.response.SuccessInfo.GET_PRODUCTS;
import static com.shoeshop.response.SuccessInfo.POST_ORDER;
import static com.shoeshop.response.SuccessInfo.SUCCESS;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shoeshop.dto.CheckOutDto;
import com.shoeshop.dto.OrderCreateDto;
import com.shoeshop.dto.OrderDto;
import com.shoeshop.dto.OrderItemDto;
import com.shoeshop.exceptions.EntityNotFoundException;
import com.shoeshop.exceptions.GlobalException;
import com.shoeshop.order.model.ProductDto;
import com.shoeshop.response.BaseResponse;
import com.shoeshop.response.DataResponse;
import com.shoeshop.service.OrderService;
import com.shoeshop.service.ProductServiceClient;
import com.shoeshop.util.JwtVerifier;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
@Tag(name = "Order", description = "Order API")
public class OrderController {
    private final OrderService orderService;
    private final ProductServiceClient productServiceClient;
    private final JwtVerifier jwtVerifier;

    @GetMapping("/throw1")
    @Operation(summary = "Throw Test 1")
    public DataResponse<String> throwError1() {
        throw new EntityNotFoundException(INVALID_INPUT);
    }

    @GetMapping("/throw2")
    @Operation(summary = "Throw Test 2")
    public DataResponse<String> throwError2() {
        try {
            throw new Exception("test");
        } catch (Exception e) {
            throw new GlobalException(INTERNAL_SERVER_ERROR, e);
        }
    }

    @GetMapping("/allproducts")
    @Operation(summary = "Get All Products")
    public DataResponse<List<ProductDto>> getProducts() {
        List<ProductDto> allProducts = productServiceClient.getAllProducts();
        return new DataResponse<>(GET_PRODUCTS, allProducts);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Order by id")
    public DataResponse<OrderDto> getOrder(@PathVariable("id") Long id) {
        log.info("Getting order with id: {}", id);
        OrderDto order = orderService.getOrder(id);
        log.info("{}", order);
        return new DataResponse<>(GET_ORDER, order);
    }

    @PostMapping
    @Operation(summary = "Create Order")
    public DataResponse<OrderDto> postOrder(@Valid @RequestBody OrderCreateDto orderCreationDto) {
        log.info("post order: {}", orderCreationDto);
        OrderDto order = orderService.createOrder(orderCreationDto);

        return new DataResponse<>(POST_ORDER, order);
    }

    @PostMapping("/addItem")
    @Operation(summary = "Add item to order")
    public DataResponse<OrderItemDto> addItemToOrder(@Valid @RequestBody OrderItemDto orderItemDto) {
        orderItemDto = orderService.addItemToOrder(orderItemDto);
        return new DataResponse<>(SUCCESS, orderItemDto);
    }

    @PostMapping("/checkout")
    @Operation(summary = "Check out")
    public DataResponse<Boolean> checkOut(
        @Valid @RequestBody CheckOutDto checkOutDto,
        @RequestHeader(name = "Authorization", required = false) String bearer) {

        String jwt = jwtVerifier.obtainJwtFromBearer(bearer);
        if (!jwt.isEmpty()) {
            log.info("JWT Token: {}", jwt);
        } else {
            log.info("No JWT token found in request headers");
            return new DataResponse<>(CHECKOUT_UNSUCCESSFUL, false);
        }

        log.info("checkOut {}", checkOutDto);
        boolean verifyJwt = this.jwtVerifier.verifyJwt(jwt);
        log.info("jwt verify result: {}", verifyJwt);
        // TODO: Call payment service to process payment
        return new DataResponse<>(CHECKOUT_SUCCESSFUL, true);
    }
}
