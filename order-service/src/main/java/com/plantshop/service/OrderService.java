package com.plantshop.service;

import static com.plantshop.response.FailureInfo.INVALID_INPUT;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.plantshop.dto.OrderCreationDto;
import com.plantshop.dto.OrderDto;
import com.plantshop.entity.Customer;
import com.plantshop.entity.Order;
import com.plantshop.exceptions.EntityNotFoundException;
import com.plantshop.repository.CustomerRepository;
import com.plantshop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public OrderDto getOrder(Long id) {
        Order o = orderRepository.findOrderById(id);
        Customer c = customerRepository.findCustomerById(o.getCustomer().getId())
                .orElseThrow(() -> new EntityNotFoundException(INVALID_INPUT));
        o.setCustomer(c);
        OrderDto orderDto = toDto(o);
        return orderDto;
    }

    @Transactional
    public OrderDto createOrder(OrderCreationDto orderCreationDto) {
        Customer c = customerRepository.findCustomerById(orderCreationDto.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException(INVALID_INPUT));
        Order newOrder = Order.builder().orderDetails(orderCreationDto.getOrderDetails())
                .customer(c).build();
        orderRepository.save(newOrder);
        return toDto(newOrder);
    }

    OrderDto toDto(Order order) {
        return OrderDto.builder() //
                .id(order.getId()) //
                .customer(order.getCustomer()) //
                .orderDetails(order.getOrderDetails()) //
                .build();
    }
}
