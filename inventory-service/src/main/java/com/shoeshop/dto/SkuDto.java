package com.shoeshop.dto;

import java.math.BigDecimal;
import com.shoeshop.entity.Sku;
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
public class SkuDto {
    private Long productId;
    private Long stockCount;
    private BigDecimal price;

    public static SkuDto from(Sku sku) {
        return SkuDto.builder() //
                .productId(sku.getProductId()) //
                .stockCount(sku.getStockCount()) //
                .price(sku.getPrice()) //
                .build();
    }
}
