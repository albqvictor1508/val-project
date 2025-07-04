package com.val.project.controller;

import com.val.project.entity.Cart;
import com.val.project.entity.CartItem;
import com.val.project.service.CartService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
  @Autowired
  private CartService cartService;

  @PostMapping
  public Cart save(@Valid @RequestBody Cart cartBody) {
    return cartService.save(cartBody);
  }

  @DeleteMapping("/:cartId")
  public void delete(@PathVariable Long cartId) {
  }

  @GetMapping("/:cartId")
  public Cart getCartById(@PathVariable Long cartId) {
    return cartService.findById(cartId);
  }

  public List<CartItem> getItemsById(@PathVariable Long cartId) {
    return cartService.getItemsById(cartId);
  }
}
