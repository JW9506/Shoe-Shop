package com.shoeshop.entity;

import java.math.BigDecimal;
import com.shoeshop.AuditEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "t_cartitem")
public class CartItem extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "cart_id")
    private Integer cartId;

    private Integer quantity;

    @Column(name = "total_price")
    private BigDecimal totalPrice;
}
