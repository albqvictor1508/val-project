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

  public Product update(Product p) {
    Product existingProduct = findById(p.getId());
    if (existingProduct == null)
      throw new RuntimeException("Product not exits");

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
