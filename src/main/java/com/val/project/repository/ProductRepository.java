package com.val.project.repository;

import com.val.project.entity.Product;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {
  @Query(value = "SELECT * FROM products p INNER JOIN categories c ON p.category_id = c.id WHERE p.category_id = :categoryId", nativeQuery = true)
  List<Product> findAllByCategoryId(@Param("categoryId") Long categoryId);
}
