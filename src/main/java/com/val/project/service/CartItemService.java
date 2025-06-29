package com.val.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.val.project.entity.CartItem;
import com.val.project.repository.CartItemRepository;

@Service
public class CartItemService {
  @Autowired
  private CartItemRepository cartItemRepository;

  public CartItem save(CartItem item) {
    return cartItemRepository.save(item);
  }

  public void delete(Long cartItemId) {
    cartItemRepository.deleteById(cartItemId);
  }

  public CartItem findById(Long cartItemId) {
    return cartItemRepository.findById(cartItemId)
        .orElseThrow(() -> new RuntimeException("The cart item with id: %s not exists".formatted(cartItemId)));
  }
}
