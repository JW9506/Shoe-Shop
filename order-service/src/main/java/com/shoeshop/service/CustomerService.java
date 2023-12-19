package com.shoeshop.service;

import static com.shoeshop.response.FailureInfo.INVALID_INPUT;
import org.springframework.stereotype.Service;
import com.shoeshop.dto.CustomerDto;
import com.shoeshop.entity.Customer;
import com.shoeshop.exceptions.EntityNotFoundException;
import com.shoeshop.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;


    @Transactional
    public CustomerDto getCustomer(Long id) {

        Customer c = customerRepository.findCustomerById(id)
                .orElseThrow(() -> new EntityNotFoundException(INVALID_INPUT));
        return CustomerDto.from(c);
        
    }
}
