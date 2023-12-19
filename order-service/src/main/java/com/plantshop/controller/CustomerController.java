package com.plantshop.controller;

import static com.plantshop.response.SuccessInfo.GET_CUSTOMER;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.plantshop.dto.CustomerDto;
import com.plantshop.response.DataResponse;
import com.plantshop.service.CustomerService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{id}")
    public DataResponse<CustomerDto> getCustomer(@PathVariable("id") Long id) {

        CustomerDto c = customerService.getCustomer(id);
        return new DataResponse<>(GET_CUSTOMER, c);
    }
}
