package com.val.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.val.project.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
  @Query(name = "SELECT * FROM Product p WHERE p.categoryId = :categoryId")
  List<Product> findByCategoryId(@Param("categoryId") Long categoryId);

  @Query(name = "SELECT * FROM Product p WHERE p.name LIKE %:productName%")
  List<Product> findByNameLike(@Param("productName") String name);
}
