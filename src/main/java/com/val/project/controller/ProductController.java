package com.val.project.controller;

import com.val.project.dto.product.ProductRequest;
import com.val.project.dto.product.ProductResponse;
import com.val.project.entity.Category;
import com.val.project.entity.Product;
import com.val.project.service.CategoryService;
import com.val.project.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
  @Autowired
  private ProductService productService;
  @Autowired
  private CategoryService categoryService;

  @PostMapping
  public ProductResponse save(@Valid @RequestBody ProductRequest body) {
    Category c = categoryService.findById(body.getCategoryId());
    Product p = productService.save(new Product(body.getName(), body.getDescription(), body.getPrice(), c));
    return new ProductResponse(p);
  }

  @DeleteMapping("/:productId")
  public void delete(@PathVariable Long productId) {
    productService.delete(productId);
  }
}
