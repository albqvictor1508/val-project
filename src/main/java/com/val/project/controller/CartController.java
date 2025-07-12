package com.val.project.controller;

import com.val.project.entity.Cart;
import com.val.project.entity.CartItem;
import com.val.project.repository.CartItemRepository;
import com.val.project.service.CartService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carts")
public class CartController {
  @Autowired
  private CartService cartService;
  @Autowired
  private CartItemRepository cartItemRepository;

  @PostMapping
  public Cart save(@Valid @RequestBody final Cart cartBody) {
    return cartService.save(cartBody);
  }

  @DeleteMapping("/{cartId}")
  public void delete(@PathVariable final Long cartId) {
  }

  @GetMapping("/{cartId}")
  public Cart getCartById(@PathVariable final Long cartId) {
    return cartService.findById(cartId);
  }

  @GetMapping("/{cartId}/items")
  public List<CartItem> getItemsById(@PathVariable final Long cartId) {
    return cartService.getItemsById(cartId);
  }

  // TODO: jogar isso aqui no service
  @PostMapping("/{cartId}/items")
  public ResponseEntity<?> addItem(@PathVariable final Long cartItemId) {
    CartItem cartItem = cartItemRepository.findById(cartItemId)
        .orElseThrow(() -> new RuntimeException("Carth  item with id: %s not exists".formatted(cartItemId)));

    Long cartId = cartItem.getCart().getId();
    Cart cart = cartService.findById(cartId);

    cart.getItems().add(cartItem);
    cartService.save(cart);
    return ResponseEntity.ok(cartItem);
  }
}
