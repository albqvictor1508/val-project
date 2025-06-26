package com.val.project.service;

import java.util.List;
import com.val.project.entity.Product;
import com.val.project.entity.Cart;
import com.val.project.repository.CartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
  private CartRepository cartRepository;

  @Autowired
  private ProductService productService;

  public Cart save(Cart cart) {
    return cartRepository.save(cart);
  }

  public Cart findById(Long cartId) {
    return cartRepository.findById(cartId)
        .orElseThrow(() -> new RuntimeException("product cart with id %s not exists".formatted(cartId)));
  }

  // TODO: trocar esse Product por um DTO
  public void addProduct(Product p) {
  }

  // TODO: trocar esse Product por um DTO
  public void deleteProduct(Product p) {
  }
}
