package com.shoeshop.controller;

import static com.shoeshop.response.SuccessInfo.GET_CUSTOMER;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.shoeshop.dto.CustomerDto;
import com.shoeshop.response.DataResponse;
import com.shoeshop.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{id}")
    public DataResponse<CustomerDto> getCustomer(@PathVariable("id") Long id, @RequestParam(required = false) List<String> fields) {

        log.info("{}", fields);
        CustomerDto customer = customerService.getCustomer(id, fields);
        return new DataResponse<>(GET_CUSTOMER, customer);
    }
}
