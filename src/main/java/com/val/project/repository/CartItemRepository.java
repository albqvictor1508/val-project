package com.val.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.val.project.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
