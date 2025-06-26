package com.val.project.controller;

import com.val.project.entity.Product;
import com.val.project.entity.Cart;
import com.val.project.service.CartService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
  @Autowired
  private CartService productCartService;

  @PostMapping
  public Cart save(@Valid @RequestBody Cart cartBody) {
    return productCartService.save(cartBody);
  }
}
