package com.shoeshop.dto;

import java.math.BigDecimal;
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
public class ProductCreateDto {

    private String name;
    private String description;
    private Long categoryId;
    private BigDecimal originalPrice;
    private BigDecimal salePrice;
    private Long rating;
    private Boolean inStock;
    private String brand;
    private String imageUrl;
}
