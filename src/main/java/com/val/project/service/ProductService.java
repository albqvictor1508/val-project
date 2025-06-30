package com.val.project.service;

import com.val.project.entity.Product;
import com.val.project.dto.product.ProductResponse;
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
  private CategoryService categoryService;

  // WARN: provavelmente vou ter que validar o carrinho tb
  public Product save(Product p) {
    categoryService.findById(p.getCategory().getId());
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

  public List<ProductResponse> findAll() {
    List<ProductResponse> response = new ArrayList<>();
    List<Product> productList = productRepository.findAll();

    for (Product p : productList) {
      response.add(new ProductResponse(p));
    }

    return response;
  }

  public Product update(Product p) {
    Product existingProduct = findById(p.getId());
    existingProduct.setName(p.getName());
    existingProduct.setDescription(p.getDescription());
    existingProduct.setPrice(p.getPrice());
    existingProduct.setCategory(p.getCategory());
    return save(existingProduct);
  }

  public void delete(Long productId) {
    Product p = findById(productId);
    productRepository.delete(p);
  }
}
