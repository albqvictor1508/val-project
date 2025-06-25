package com.val.project.service;

import org.springframework.stereotype.Service;

import com.val.project.entity.Category;
import com.val.project.repository.CategoryRepository;

@Service
public class CategoryService {
  private CategoryRepository categoryRepository;

  public Category findById(Long id) {
    return categoryRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("The category with id: %s not exists".formatted(id)));
  }
}
