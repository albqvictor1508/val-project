package com.val.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.val.project.entity.Category;
import com.val.project.repository.CategoryRepository;

@RestController
@RequestMapping("/category")
public class CategoryController {
  @Autowired
  private CategoryRepository categoryRepository;

  @GetMapping
  public List<Category> findAll() {
    return categoryRepository.findAll();
  }
}
