package com.val.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.val.project.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
