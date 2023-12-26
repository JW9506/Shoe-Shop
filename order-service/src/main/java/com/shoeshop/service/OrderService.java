package com.shoeshop.service;

import static com.shoeshop.response.FailureInfo.INVALID_INPUT;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.shoeshop.dto.CustomerDto;
import com.shoeshop.dto.OrderCreateDto;
import com.shoeshop.dto.OrderDto;
import com.shoeshop.dto.OrderItemDto;
import com.shoeshop.entity.Customer;
import com.shoeshop.entity.Order;
import com.shoeshop.entity.OrderItem;
import com.shoeshop.exceptions.EntityNotFoundException;
import com.shoeshop.repository.CustomerRepository;
import com.shoeshop.repository.OrderItemRepository;
import com.shoeshop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import static com.shoeshop.enums.PaymentStatus.*;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public OrderDto getOrder(Long id) {
        Order o = orderRepository.findOrderById(id)
                .orElseThrow(() -> new EntityNotFoundException(INVALID_INPUT));
        OrderDto orderDto = OrderDto.from(o);
        return orderDto;
    }

    @Transactional
    public OrderDto createOrder(OrderCreateDto orderCreationDto) {
        Customer c = customerRepository.findCustomerById(orderCreationDto.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException(INVALID_INPUT));
        Order newOrder = Order.builder() //
                .orderDetails(orderCreationDto.getOrderDetails()) //
                .paymentStatus(UNPAID) //
                .customer(c) //
                .build();
        orderRepository.save(newOrder);
        return OrderDto.from(newOrder);
    }

    @Transactional
    public OrderItemDto addItemToOrder(OrderItemDto orderItemDto) {
        Order order = orderRepository.findById(orderItemDto.getOrderId()).orElseThrow(() -> new EntityNotFoundException(INVALID_INPUT));
        OrderItem orderItem = OrderItem.builder()
                .order(order)
                .productId(orderItemDto.getProductId())
                .quantity(orderItemDto.getQuantity())
                .totalPrice(orderItemDto.getTotalPrice())
                .build();
        orderItemRepository.save(orderItem);
        return OrderItemDto.from(orderItem);
    }
}
