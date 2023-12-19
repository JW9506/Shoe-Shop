package com.shoeshop.service;

import static com.shoeshop.response.FailureInfo.INVALID_INPUT;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.shoeshop.dto.CustomerDto;
import com.shoeshop.dto.OrderCreationDto;
import com.shoeshop.dto.OrderDto;
import com.shoeshop.entity.Customer;
import com.shoeshop.entity.Order;
import com.shoeshop.exceptions.EntityNotFoundException;
import com.shoeshop.repository.CustomerRepository;
import com.shoeshop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import static com.shoeshop.enums.PaymentStatus.*;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public OrderDto getOrder(Long id) {
        Order o = orderRepository.findOrderById(id)
                .orElseThrow(() -> new EntityNotFoundException(INVALID_INPUT));
        Customer c = customerRepository.findCustomerById(o.getCustomer().getId())
                .orElseThrow(() -> new EntityNotFoundException(INVALID_INPUT));
        OrderDto orderDto = OrderDto.from(o);
        orderDto.setCustomer(CustomerDto.from(c));
        return orderDto;
    }

    @Transactional
    public OrderDto createOrder(OrderCreationDto orderCreationDto) {
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
}
