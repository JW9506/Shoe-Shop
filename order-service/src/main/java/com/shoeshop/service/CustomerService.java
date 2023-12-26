package com.shoeshop.service;

import static com.shoeshop.response.FailureInfo.INVALID_INPUT;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.shoeshop.dto.CustomerDto;
import com.shoeshop.dto.OrderDto;
import com.shoeshop.entity.Customer;
import com.shoeshop.entity.Order;
import com.shoeshop.exceptions.EntityNotFoundException;
import com.shoeshop.repository.CustomerRepository;
import com.shoeshop.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;


    @Transactional
    public CustomerDto getCustomer(Long id, List<String> fields) {

        Customer c = customerRepository.findCustomerById(id)
                .orElseThrow(() -> new EntityNotFoundException(INVALID_INPUT));
        CustomerDto customerDto = CustomerDto.from(c);
        
        if (fields != null) {
            List<Order> orders = orderRepository.findByCustomerId(c.getId()).orElseThrow(() -> new EntityNotFoundException(INVALID_INPUT));
            if (fields.contains("orders")) {
                customerDto.setOrders(new ArrayList<>());
                orders.forEach(order -> {
                    customerDto.getOrders().add("" + order.getId());
                });
            } else if (fields.contains("ordersPlus")) {
                customerDto.setOrdersPlus(new ArrayList<>());
                orders.forEach(order -> {
                    customerDto.getOrdersPlus().add(OrderDto.from(order));
                });
            }
        }

        return customerDto;
    }
}
