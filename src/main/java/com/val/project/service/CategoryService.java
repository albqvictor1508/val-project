package com.val.project.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.val.project.dto.category.CategoryRequest;
import com.val.project.dto.category.CategoryResponse;
import com.val.project.dto.product.ProductResponse;
import com.val.project.entity.Category;
import com.val.project.entity.Product;
import com.val.project.repository.CategoryRepository;
import com.val.project.utils.Parse;

@Service
public class CategoryService {
  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private ProductService productService;

  public List<CategoryResponse> findAll() {
    List<CategoryResponse> response = new ArrayList<>();
    List<Category> categories = categoryRepository.findAll();

    for (Category c : categories) {
      response.add(new CategoryResponse(c));
    }

    return response;
  }

  public Category save(CategoryRequest body) {
    StringBuilder capitalize = new StringBuilder();
    String[] nameSplited = body.getName().split(" ");

    for (String word : nameSplited) {
      capitalize.append(Character.toUpperCase(word.charAt(0)));
      if (word.length() > 1) {
        capitalize.append(word.substring(1).toLowerCase());
      }
      capitalize.append(" ");
    }

    final String name = capitalize.toString().trim();
    final String slug = Parse.removeAccents(name).replace(" ", "-").toLowerCase();

    final Category categoryExists = categoryRepository.findByName(name).orElse(null);

    if (categoryExists != null)
      throw new RuntimeException("The category with name: %s already exists".formatted(body.getName()));

    final Category category = new Category(name, slug);
    category.setCreatedAt(LocalDateTime.now());
    category.setUpdatedAt(LocalDateTime.now());
    return categoryRepository.save(category);
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
  }

  public Boolean existsByName(String name) {
    return categoryRepository.existsByName(name);
  }

  public Category update(Category c) {
    c.setUpdatedAt(LocalDateTime.now());
    return categoryRepository.save(c);
  }

  public List<ProductResponse> findProductsByCategoryId(Long categoryId) {
    Category c = findById(categoryId);
    return productService.findProductsByCategoryId(c.getId());
  }
}
