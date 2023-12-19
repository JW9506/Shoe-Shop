package com.plantshop.service;

import static com.plantshop.response.FailureInfo.INVALID_INPUT;
import org.springframework.stereotype.Service;
import com.plantshop.dto.CustomerDto;
import com.plantshop.entity.Customer;
import com.plantshop.exceptions.EntityNotFoundException;
import com.plantshop.repository.CustomerRepository;
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
