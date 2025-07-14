package com.val.project.service;

import com.val.project.entity.Product;
import com.val.project.dto.product.ProductResponse;
import com.val.project.repository.CategoryRepository;
import com.val.project.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private CategoryRepository categoryRepository;

  // WARN: provavelmente vou ter que validar o carrinho tb
  public Product save(Product p) {
    final Long categoryId = p.getCategory().getId();
    categoryRepository.findById(categoryId)
        .orElseThrow(
            () -> new RuntimeException("The category with id: %s not exists".formatted(categoryId)));

    p.setCreatedAt(LocalDateTime.now());
    p.setUpdatedAt(LocalDateTime.now());
    return productRepository.save(p);
  }

  public Product findById(Long productId) {
    return productRepository.findById(productId)
        .orElseThrow(() -> new RuntimeException("product with id %s not exists".formatted(productId)));
  }

  public Boolean existsById(Long productId) {
    return productRepository.existsById(productId);
  }

  // WARN: SQL (LIKE) pra achar produto com case-insensitive e ignorando acento
  // gráfico
  public List<Product> findByNameContainingIgnoreCase(String productName) {
    return productRepository.findByNameContainingIgnoreCase(productName);
  }

  public List<ProductResponse> findAll() {
    final List<ProductResponse> response = new ArrayList<>();
    final List<Product> productList = productRepository.findAll();

    for (Product p : productList) {
      response.add(new ProductResponse(p));
    }

    return response;
  }

  public Product update(Product p) {
    final Product existingProduct = findById(p.getId());
    existingProduct.setName(p.getName());
    existingProduct.setDescription(p.getDescription());
    existingProduct.setPrice(p.getPrice());
    existingProduct.setCategory(p.getCategory());
    return save(existingProduct);
  }

  public void delete(Long productId) {
    final Product p = findById(productId);
    productRepository.delete(p);
  }

  public List<ProductResponse> findProductsByCategoryId(Long categoryId) {
    final List<ProductResponse> response = new ArrayList<>();
    final List<Product> products = productRepository.findByCategoryId(categoryId);

    for (Product p : products) {
      response.add(new ProductResponse(p));
    }

    return response;
  }
}
