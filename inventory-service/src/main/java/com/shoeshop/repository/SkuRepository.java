package com.shoeshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.shoeshop.entity.Sku;

@Repository
public interface SkuRepository extends JpaRepository<Sku, Long> {
  
}
