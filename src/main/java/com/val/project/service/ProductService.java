package com.val.project.service;

import com.val.project.entity.Product;
import com.val.project.repository.ProductRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
  private ProductRepository productRepository;
  @Autowired
  private CategoryService categoryService;

  // WARN: provavelmente vou ter que validar o carrinho tb
  public Product save(Product p) {
    categoryService.findById(p.getCategory().getId());
    return productRepository.save(p);
  }

  public Product findById(Long productId) {
    return productRepository.findById(productId)
        .orElseThrow(() -> new RuntimeException("product with id %s not exists".formatted(productId)));
  }

  public List<Product> findByCategory(String category) {
    return productRepository.findByCategory(category)
        .orElseThrow(() -> new RuntimeException("this category not exists"));
  }

  public List<Product> findAll() {
    return productRepository.findAll();
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
