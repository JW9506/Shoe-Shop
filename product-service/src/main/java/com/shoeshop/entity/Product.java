package com.shoeshop.entity;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "t_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "parent_category_id", nullable = false)
    private Category category;

    @Column(name = "original_price")
    private BigDecimal originalPrice;

    @Column(name = "sale_price")
    private BigDecimal salePrice;

    @Column
    private Long rating;

    @Column(name = "is_in_stock")
    private Boolean inStock;

    @Column
    private String brand;

    @Column
    private String imageUrl;
}
