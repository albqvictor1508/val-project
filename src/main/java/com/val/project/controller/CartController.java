package com.val.project.controller;

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

import com.val.project.dto.cart.AddItemRequest;
import com.val.project.dto.cart.CartRequest;
import com.val.project.entity.Cart;
import com.val.project.entity.CartItem;
import com.val.project.service.CartService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/carts")
public class CartController {
  @Autowired
  private CartService cartService;

  @PostMapping
  public Cart createOrGetCart(@Valid @RequestBody final CartRequest cartBody) {
    return cartService.findOrCreateCartForUser(cartBody.getUserId(), cartBody.getSessionId());
  }

  @DeleteMapping("/{cartId}")
  public void delete(@PathVariable final Long cartId) {
    cartService.deleteCart(cartId);
  }

  @GetMapping("/{cartId}")
  public Cart getCartById(@PathVariable final Long cartId) {
    return cartService.findById(cartId);
  }

  @GetMapping("/{cartId}/items")
  public List<CartItem> getItemsById(@PathVariable final Long cartId) {
    return cartService.getItemsById(cartId);
  }

  @PostMapping("/{cartId}/items")
  public ResponseEntity<CartItem> addItemToCart(@PathVariable final Long cartId,
      @Valid @RequestBody AddItemRequest request) {
    CartItem cartItem = cartService.addItem(cartId, request);
    return ResponseEntity.ok(cartItem);
  }
}
