package com.plantshop.controller;

import static com.plantshop.response.SuccessInfo.GET_ORDER;
import static com.plantshop.response.SuccessInfo.POST_ORDER;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.plantshop.dto.OrderCreationDto;
import com.plantshop.dto.OrderDto;
import com.plantshop.response.DataResponse;
import com.plantshop.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{id}")
    public DataResponse<OrderDto> getOrder(@PathVariable("id") Long id) {
        log.info("Getting order with id: {}", id);
        OrderDto order = orderService.getOrder(id);
        log.info("{}", order);
        return new DataResponse<>(GET_ORDER, order);
    }

    @PostMapping
    public DataResponse<OrderDto> postOrder(@Valid @RequestBody OrderCreationDto orderCreationDto) {
        log.info("post order: {}", orderCreationDto);
        OrderDto order = orderService.createOrder(orderCreationDto);

        return new DataResponse<>(POST_ORDER, order);
    }
}
