package com.plantshop.dto;

import com.plantshop.entity.Product;
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
public class ProductDto {

    private String name;
    private String description;
    private String parentCategoryName;
    private Double price;

    public static ProductDto from(Product product) {
        return ProductDto.builder() //
                .description(product.getDescription())
                .name(product.getName())
                .price(product.getPrice())
                .parentCategoryName(product.getCategory().getName())
                .build();
    }

}
