package com.shoeshop.dto;

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

    public static CustomerDto from(Customer c) {
        return CustomerDto.builder() //
                .name(c.getName()) //
                .email(c.getEmail()) //
                .build();
    }

}
