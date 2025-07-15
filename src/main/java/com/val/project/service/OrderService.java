package com.val.project.service;

import com.val.project.dto.order.OrderRequest;
import com.val.project.dto.order.OrderResponse;
import com.val.project.entity.Cart;
import com.val.project.entity.Order;
import com.val.project.entity.OrderItem;
import com.val.project.repository.OrderRepository;
import com.val.project.types.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
  @Autowired
  private OrderRepository orderRepository;
  @Autowired
  private CartService cartService;

  @Transactional
  public OrderResponse checkout(OrderRequest orderRequest) {
    Cart cart = cartService.findByUserId(orderRequest.getUser().getId());

    Order order = new Order();
    order.setUser(orderRequest.getUser());
    order.setShippingAddress(orderRequest.getShippingAddress());
    order.setStatus(OrderStatus.PENDING);
    order.setTotalPrice(cart.getTotal());

    List<OrderItem> orderItems = cart.getItems().stream()
        .map(cartItem -> {
          OrderItem orderItem = new OrderItem();
          orderItem.setOrder(order);
          orderItem.setProduct(cartItem.getProduct());
          orderItem.setQuantity(cartItem.getQuantity());
          orderItem.setUnitPrice(cartItem.getUnitPrice());
          return orderItem;
        })
        .collect(Collectors.toList());
    order.setOrderItems(orderItems);
    orderRepository.save(order);
    cartService.deleteCart(cart.getId());

    return new OrderResponse(
        order.getId(),
        order.getUser(),
        order.getShippingAddress(),
        order.getStatus(),
        order.getTotalPrice(),
        order.getCreatedAt());
  }

  public void delete(Long orderId) {
    orderRepository.deleteById(orderId);
  }
}
