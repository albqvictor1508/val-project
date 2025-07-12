package com.val.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.val.project.entity.Order;
import com.val.project.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {
  @Autowired
  private OrderService orderService;

  // TODO: CRIAR O ORDER QUANDO O CHECKOUT FOR FEITO, E CRIAR UM DTO PRA ISSO
  public ResponseEntity<Order> createOrder(@Valid @RequestBody Order o) {
    orderService.createOrder(o);
    return ResponseEntity.ok(o);
  }

  public ResponseEntity<?> deleteOrder(@Valid @RequestBody Long orderId) {
    orderService.delete(orderId);
    return ResponseEntity.status(200).build();
  }
}
