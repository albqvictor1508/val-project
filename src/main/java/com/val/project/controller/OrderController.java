package com.val.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.val.project.dto.order.OrderRequest;
import com.val.project.dto.order.OrderResponse;
import com.val.project.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {
  @Autowired
  private OrderService orderService;

  @PostMapping("/checkout")
  public ResponseEntity<OrderResponse> checkout(@Valid @RequestBody OrderRequest orderRequest) {
    OrderResponse orderResponse = orderService.checkout(orderRequest);
    return ResponseEntity.ok(orderResponse);
  }

  @DeleteMapping("/{orderId}")
  public ResponseEntity<?> deleteOrder(@PathVariable Long orderId) {
    orderService.delete(orderId);
    return ResponseEntity.status(200).build();
  }
}
