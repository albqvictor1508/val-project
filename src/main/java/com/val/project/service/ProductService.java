package com.val.project.service;

import com.val.project.entity.Product;
import com.val.project.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
  private ProductRepository productRepository;

  public Product save(Product p) {
    return productRepository.save(p);
  }

  public Product findById(Long productId) {
    return productRepository.findById(productId).orElse(null);
  }
}
