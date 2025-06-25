package com.val.project.controller;

import com.val.project.entity.Product;
import com.val.project.service.ProductService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
  @Autowired
  private ProductService productService;

  @GetMapping("/:categorySlug/products")
  public List<Product> findByCategorySlug(@Param("categorySlug") String categorySlug) {
    return productService.findByCategorySlug(categorySlug);
  }
}
