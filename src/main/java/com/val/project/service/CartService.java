package com.val.project.service;

import java.util.List;
import com.val.project.entity.Product;
import com.val.project.entity.Cart;
import com.val.project.repository.CartRepository;
import com.val.project.repository.ProductCartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
  private CartRepository cartRepository;

  @Autowired
  private ProductService productService;

  public Cart save(ProductCart productCart) {
    return cartRepository.save(productCart);
  }

  public Cart findById(Long cartId) {
    return cartRepository.findById(cartId)
        .orElseThrow(() -> new RuntimeException("product cart with id %s not exists".formatted(cartId)));
  }

  // TODO: trocar esse Product por um DTO
  public void addProduct(Product p) {
    Product product = productService.findById(p.getId());

    final Long cartId = p.getCart().getId();
    Cart cart = findById(cartId);

    cart.getProducts().add(product);
    cartRepository.save(cart);
  }

  // TODO: trocar esse Product por um DTO
  public void deleteProduct(Product p) {
  }
}
