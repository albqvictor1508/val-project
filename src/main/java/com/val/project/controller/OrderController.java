package com.val.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.val.project.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
  @Autowired
  private OrderService orderService;

  @DeleteMapping("/{orderId}")
  public ResponseEntity<?> deleteOrder(@PathVariable Long orderId) {
    orderService.delete(orderId);
    return ResponseEntity.status(200).build();
  }
}
