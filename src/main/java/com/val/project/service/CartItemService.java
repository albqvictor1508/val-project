package com.val.project.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.val.project.entity.Cart;
import com.val.project.entity.CartItem;
import com.val.project.entity.Product;
import com.val.project.repository.CartItemRepository;

@Service
public class CartItemService {
  @Autowired
  private CartItemRepository cartItemRepository;

  public CartItem upsertCartItem(Cart cart, Product product, Integer qtd) {
    Optional<CartItem> existingItemOpt = cart.getItems().stream()
        .filter(item -> item.getProduct().getId().equals(product.getId()))
        .findFirst();

    if (existingItemOpt.isPresent()) {
      CartItem item = existingItemOpt.get();
      item.setQuantity(item.getQuantity() + qtd);
      item.setUpdatedAt(LocalDateTime.now());
      return cartItemRepository.save(item);
    }

    CartItem newItem = new CartItem();
    newItem.setCart(cart);
    newItem.setProduct(product);
    newItem.setQuantity(qtd);
    newItem.setUnitPrice(product.getPrice());
    newItem.setCreatedAt(LocalDateTime.now());
    newItem.setUpdatedAt(LocalDateTime.now());
    cart.getItems().add(newItem);
    return cartItemRepository.save(newItem);
  }
}
