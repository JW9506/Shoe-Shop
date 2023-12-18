package com.plantshop.model.vo;

import java.math.BigDecimal;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderCreateVO {

    private String customerId;

    @NotNull
    @Min(value = 0, message = "total price cannot be less than 0 ")
    private BigDecimal totalPrice;

    @NotNull
    private String payMethod;

    private String buyerComment;

    private String sellerComment;

    private Integer autoConfirmDays;
}

