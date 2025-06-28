package com.val.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.val.project.dto.CategoryRequest;
import com.val.project.entity.Category;
import com.val.project.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {
  @Autowired
  private CategoryService categoryService;

  @GetMapping
  public List<Category> findAll() {
    return categoryService.findAll();
  }

  @PostMapping
  public Category save(@Valid @RequestBody CategoryRequest body) {
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
    String slug = name.replace(" ", "-").toLowerCase();
    Category category = new Category(name, slug);
    return categoryService.save(category);
  }

  @GetMapping("/:categoryId")
  public Category findById(@PathVariable Long categoryId) {
    return categoryService.findById(categoryId);
  }

  @DeleteMapping("/:categoryId")
  public void delete(@PathVariable Long categoryId) {
    categoryService.delete(categoryId);
  }
}
