package com.val.project.controller;

import com.val.project.entity.Product;
import com.val.project.service.ProductService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping("/:categorySlug/products")
  public List<Product> findByCategorySlug(@PathVariable String categorySlug) {
    return productService.findByCategorySlug(categorySlug);
  }

  @PostMapping
  public Product save(@Valid @RequestBody Product p) {
    return productService.save(p);
  }

  @DeleteMapping("/:productId")
  public void delete(@PathVariable Long productId) {
    productService.delete(productId);
  }
}
