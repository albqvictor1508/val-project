package com.val.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.val.project.entity.Category;
import com.val.project.repository.CategoryRepository;

@Service
public class CategoryService {
  @Autowired
  private CategoryRepository categoryRepository;

  public List<Category> findAll() {
    return categoryRepository.findAll();
  }

  // TODO: mudar category pra DTO
  public Category save(Category c) {
    Category categoryExists = categoryRepository.findByName(c.getName()).orElse(null);

    if (categoryExists != null)
      throw new RuntimeException("The category with name: %s already exists".formatted(c.getName()));

    return categoryRepository.save(c);
  }

  public Category findById(Long id) {
    return categoryRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("The category with id: %s not exists".formatted(id)));
  }

  public Category findBySlug(String slug) {
    return categoryRepository.findBySlug(slug)
        .orElseThrow(() -> new RuntimeException("The category with slug: %s not exists".formatted(slug)));
  }

  public Category findByName(String name) {
    return categoryRepository.findByName(name)
        .orElseThrow(() -> new RuntimeException("The category with name: %s not exists".formatted(name)));
  }

  public void delete(Long categoryId) {
    categoryRepository.deleteById(categoryId);
    ;
  }
}
