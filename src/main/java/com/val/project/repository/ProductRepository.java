package com.val.project.repository;

import com.val.project.entity.Product;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
