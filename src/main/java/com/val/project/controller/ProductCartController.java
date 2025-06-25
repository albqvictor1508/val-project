package com.val.project.controller;

import com.val.project.entity.Product;
import com.val.project.entity.ProductCart;
import com.val.project.service.ProductCartService;

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
public class ProductCartController {
  @Autowired
  private ProductCartService productCartService;

  @PostMapping
  public ProductCart save(@Valid @RequestBody ProductCart cartBody) {
    return productCartService.save(cartBody);
  }

  @GetMapping
  public List<Product> getProducts(Long cartId) {
    return productCartService.getProducts(cartId);
  }
}
