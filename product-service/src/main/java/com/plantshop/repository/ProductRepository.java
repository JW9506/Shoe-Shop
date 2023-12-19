package com.plantshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.plantshop.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
