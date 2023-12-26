package com.shoeshop.service;

import static com.shoeshop.response.FailureInfo.INVALID_INPUT;
import java.util.List;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
    public ObjectNode getCustomer(Long id, List<String> fields) {

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode customerNode = mapper.createObjectNode();

        Customer c = customerRepository.findCustomerById(id)
                .orElseThrow(() -> new EntityNotFoundException(INVALID_INPUT));
        customerNode.put("id", c.getId());
        customerNode.put("name", c.getName());
        customerNode.put("email", c.getEmail());
        
        if (fields != null && fields.contains("orders")) {
            List<Order> orders = orderRepository.findByCustomerId(c.getId()).orElseThrow(() -> new EntityNotFoundException(INVALID_INPUT));
            ArrayNode orderArray = customerNode.putArray("orders");

            orders.forEach(order -> {
                ObjectNode orderNode = mapper.createObjectNode();
                orderNode.put("orderId", order.getId());
                orderArray.add(orderNode);
            });
        }

        return customerNode;
    }
}
