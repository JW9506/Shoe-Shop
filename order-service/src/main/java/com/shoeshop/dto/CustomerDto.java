package com.shoeshop.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.shoeshop.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private String name;
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL) 
    private List<String> orders;
    @JsonInclude(JsonInclude.Include.NON_NULL) 
    private List<OrderDto> ordersPlus;

    public static CustomerDto from(Customer c) {
        return CustomerDto.builder() //
                .name(c.getName()) //
                .email(c.getEmail()) //
                .build();
    }

}
