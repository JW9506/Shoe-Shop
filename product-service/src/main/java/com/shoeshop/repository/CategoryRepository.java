package com.shoeshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.shoeshop.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
