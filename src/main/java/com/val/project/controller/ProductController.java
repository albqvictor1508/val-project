package com.val.project.controller;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.val.project.dto.product.ProductRequest;
import com.val.project.dto.product.ProductResponse;
import com.val.project.entity.Category;
import com.val.project.entity.Product;
import com.val.project.service.CategoryService;
import com.val.project.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
  @Autowired
  private ProductService productService;
  @Autowired
  private CategoryService categoryService;

  @GetMapping
  public List<ProductResponse> findAll() {
    return productService.findAll();
  }

  @PostMapping
  public ProductResponse save(@Valid @RequestBody ProductRequest body) {
    Category c = categoryService.findById(body.getCategoryId());
    Product p = productService.save(new Product(body.getName(), body.getDescription(), body.getPrice(), c));
    p.setCreatedAt(LocalDateTime.now());
    p.setUpdatedAt(LocalDateTime.now());
    return new ProductResponse(p);
  }

  @DeleteMapping("/{productId}")
  public void delete(@PathVariable Long productId) {
    productService.delete(productId);
  }

  // TODO: mudar isso futuramente pra fazer uma pesquisa semântica se necessário
  @GetMapping("/{productName}")
  public Set<ProductResponse> findByNameContainingIgnoreCase(@PathVariable String productName) {
    Set<ProductResponse> productsResponse = new HashSet<ProductResponse>();
    List<Product> products = productService.findByNameContainingIgnoreCase(productName);

    for (Product p : products) {
      productsResponse.add(new ProductResponse(p));
    }

    return productsResponse;
  }

  @PutMapping("/{productId}")
  public ProductResponse update(@PathVariable Long productId, @Valid @RequestBody ProductRequest body) {
    Product p = productService.findById(productId);
    Category c = categoryService.findById(body.getCategoryId());

    p.setName(body.getName());
    p.setCategory(c);
    p.setDescription(body.getDescription());
    p.setPrice(body.getPrice());

    Product newProduct = productService.save(p);
    return new ProductResponse(newProduct);
  }
}
