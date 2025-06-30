package com.val.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.val.project.dto.CategoryRequest;
import com.val.project.entity.Category;
import com.val.project.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {
  @Autowired
  private CategoryService categoryService;

  @GetMapping
  public List<Category> findAll() {
    return categoryService.findAll();
  }

  @PostMapping
  public Category save(@Valid @RequestBody CategoryRequest body) {
    Boolean categoryExists = categoryService.existsByName(body.getName());
    if (categoryExists)
      throw new RuntimeException("The category with name: %s already exists".formatted(body.getName()));

    return categoryService.save(body);
  }

  @GetMapping("/{categoryId}")
  public Category findById(@PathVariable Long categoryId) {
    return categoryService.findById(categoryId);
  }

  @DeleteMapping("/{categoryId}")
  public void delete(@PathVariable Long categoryId) {
    categoryService.delete(categoryId);
  }

  @PutMapping("/{categoryId}")
  public Category update(@PathVariable Long categoryId, @Valid @RequestBody CategoryRequest body) {
    Category c = categoryService.findById(categoryId);
    c.setName(body.getName());
    return categoryService.update(c);
  }
}
