package com.val.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.val.project.entity.Order;
import com.val.project.service.OrderService;

import jakarta.validation.Valid;

@RestController
public class OrderController {
  @Autowired
  private OrderService orderService;

  // TODO: CRIAR O ORDER QUANDO O CHECKOUT FOR FEITO, E CRIAR UM DTO PRA ISSO
  public ResponseEntity<Order> createOrder(@Valid @RequestBody Order o) {
    return ResponseEntity.ok(o);
  }
}
