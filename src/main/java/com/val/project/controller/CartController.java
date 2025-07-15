package com.val.project.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.val.project.dto.cartItems.CartItemResponse;
import com.val.project.entity.Cart;
import com.val.project.entity.CartItem;
import com.val.project.entity.User;
import com.val.project.service.CartService;
import com.val.project.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/carts")
public class CartController {
  @Autowired
  private CartService cartService;
  @Autowired
  private UserService userService;

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
  public Set<CartItemResponse> getItemsById(@PathVariable final Long cartId) {
    Set<CartItemResponse> itemsResponse = new HashSet<CartItemResponse>();
    List<CartItem> items = cartService.getItemsById(cartId);

    for (CartItem item : items) {
      itemsResponse.add(new CartItemResponse(item));
    }

    return itemsResponse;
  }

  @PostMapping("/{cartId}/items")
  public ResponseEntity<CartItemResponse> addItemToCart(@PathVariable final Long cartId,
      @Valid @RequestBody AddItemRequest request) {
    CartItem cartItem = cartService.addItem(cartId, request);

    return ResponseEntity.ok(new CartItemResponse(cartItem));
  }

  @PostMapping("/{cartId}/checkout")
  public ResponseEntity<?> checkout(@PathVariable Long cartId) {
    Cart cart = cartService.findById(cartId);
    Long userId = cart.getUser().getId();
    User user = userService.findById(userId);
    Double price = cart.getTotal();

    return ResponseEntity.ok(cart);
  }
}
