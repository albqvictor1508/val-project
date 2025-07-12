package com.val.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.val.project.entity.Order;
import com.val.project.repository.OrderRepository;

@Service
public class OrderService {
  @Autowired
  private OrderRepository orderRepository;

  public Order createOrder(Order o) {
    return orderRepository.save(o);
  }

  public void delete(Long orderId) {
    orderRepository.deleteById(orderId);
  }
}
