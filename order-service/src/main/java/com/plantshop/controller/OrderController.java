package com.plantshop.controller;

import static com.plantshop.response.SuccessInfo.GET_ORDER;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.plantshop.response.DataResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @GetMapping("/{id}")
    public DataResponse<String> getOrder(@PathVariable("id") String id) {
        log.info("Getting order with id: {}", id);
        return new DataResponse<>(GET_ORDER, id);
    }
}
