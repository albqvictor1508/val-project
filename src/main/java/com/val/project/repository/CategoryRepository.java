package com.val.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.val.project.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  Optional<Category> findByName(String name);

  @Query(value = "SELECT * FROM Category c WHERE c.product_id = :productId")
  Optional<List<Category>> findAllByProductId(@Param("productId") Long productId);
}
