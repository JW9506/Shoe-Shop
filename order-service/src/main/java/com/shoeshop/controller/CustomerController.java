package com.shoeshop.controller;

import static com.shoeshop.response.SuccessInfo.GET_CUSTOMER;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shoeshop.dto.CustomerDto;
import com.shoeshop.response.DataResponse;
import com.shoeshop.service.CustomerService;
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
