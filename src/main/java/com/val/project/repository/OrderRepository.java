package com.val.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.val.project.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
