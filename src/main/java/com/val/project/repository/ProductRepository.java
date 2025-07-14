package com.val.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.val.project.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
  @Query(name = "SELECT * FROM Product p WHERE p.categoryId = :categoryId")
  List<Product> findByCategoryId(@Param("categoryId") Long categoryId);

  // puxar do banco mesmo se o nome tiver acentuado, diferente do nome
  @Query(value = "SELECT * FROM Product p WHERE unaccent(LOWER(p.name)) LIKE unaccent(LOWER(CONCAT('%', :productName, '%')))", nativeQuery = true)
  List<Product> findByNameContainingIgnoreCase(@Param("productName") String productName);
}
