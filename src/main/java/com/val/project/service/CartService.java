package com.val.project.service;

import java.util.List;
import com.val.project.entity.CartItem;
import com.val.project.entity.Cart;
import com.val.project.repository.CartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
  @Autowired
  private CartRepository cartRepository;

  @Autowired
  private CartItemService cartItemService;

  public Cart save(Cart cart) {
    return cartRepository.save(cart);
  }

  public Cart findById(Long cartId) {
    return cartRepository.findById(cartId)
        .orElseThrow(() -> new RuntimeException("product cart with id %s not exists".formatted(cartId)));
  }

  public List<CartItem> getItemsById(Long cartId) {
    Cart cart = findById(cartId);
    return cart.getItems();
  }

  public void addItem(CartItem item) {
    Cart cart = this.findById(item.getCart().getId());
    if (cartItemService.existsById(item.getId())) {
      item.setQuantity(item.getQuantity() + 1);
      cartRepository.save(cart);
      return;
    }
    cart.getItems().add(item);
    cartRepository.save(cart);
  }

  public void deleteItem(CartItem item) {
    Cart cart = this.findById(item.getCart().getId());
    if (cartItemService.existsById(item.getId())) {
      item.setQuantity(item.getQuantity() - 1);
      cartRepository.save(cart);
      return;
    }
    cart.getItems().remove(item);
    cartRepository.save(cart);

  }
}
