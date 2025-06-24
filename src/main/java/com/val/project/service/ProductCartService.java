package com.val.project.service;

import com.val.project.entity.Product;
import com.val.project.entity.ProductCart;
import com.val.project.repository.ProductCartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCartService {
  private ProductCartRepository productCartRepository;

  @Autowired
  private ProductService productService;

  public ProductCart findById(Long cartId) {
    return productCartRepository.findById(cartId).orElse(null);
  }

  // TODO: trocar esse Product por um DTO
  public void addProduct(Product p) {
    Product product = productService.findById(p.getId());

    if (product == null)
      throw new RuntimeException("product not exists");

    final Long cartId = p.getCart().getId();
    var cart = findById(cartId);

    if (cart == null)
      throw new RuntimeException("cart not exists");

    cart.getProducts().add(product);
  }

  public void deleteProduct(Product p) {
    Product product = productService.findById(p.getId());

    if (product == null)
      throw new RuntimeException("product not exists");

    final Long cartId = p.getCart().getId();
    var cart = findById(cartId);

    if (cart == null)
      throw new RuntimeException("cart not exists");

    cart.getProducts().remove(product);
  }
}
