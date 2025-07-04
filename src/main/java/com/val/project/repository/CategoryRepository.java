package com.val.project.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.val.project.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  Optional<Category> findByName(String name);

  Optional<Category> findBySlug(String slug);

  Boolean existsByName(String name);
}
