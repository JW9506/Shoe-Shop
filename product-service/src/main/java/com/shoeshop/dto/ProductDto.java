package com.shoeshop.dto;

import java.math.BigDecimal;
import com.shoeshop.entity.Product;
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

    private String id;
    private String name;
    private String description;
    private String categoryName;
    private BigDecimal originalPrice;
    private BigDecimal salePrice;
    private Long rating;
    private Boolean inStock;
    private String brand;
    private String imageUrl;

    public static ProductDto from(Product product) {
        return ProductDto.builder() //
                .id("" + product.getId())
                .description(product.getDescription())
                .name(product.getName())
                .categoryName(product.getCategory().getName())
                .originalPrice(product.getOriginalPrice())
                .salePrice(product.getSalePrice())
                .rating(product.getRating())
                .inStock(product.getInStock())
                .brand(product.getBrand())
                .imageUrl(product.getImageUrl())
                .build();
    }

}
