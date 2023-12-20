package com.shoeshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.shoeshop.entity.Cart;

@Repository
public interface CartItem extends JpaRepository<Cart, Long> {
}
