package com.shoeshop.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.shoeshop.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.id = :id")
    Optional<Order> findOrderById(@Param("id") Long id);

    @Query("SELECT o FROM Order o WHERE o.customer.id = :customerId")
    Optional<List<Order>> findByCustomerId(Long customerId);
}