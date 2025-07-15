package com.val.project.dto.order;

import com.val.project.entity.Order;
import com.val.project.types.OrderStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.val.project.dto.order.OrderItemResponse;

@Getter
@Setter
public class OrderResponse {
  @NotNull
  private Long id;
  @NotNull
  private Long userId;
  @NotNull
  private Long shippingAddressId;
  @NotNull
  @NotBlank
  private OrderStatus status;
  @NotNull
  private Double totalPrice;
  @NotNull
  private LocalDateTime createdAt;
  private List<OrderItemResponse> orderItems;

  public OrderResponse(Order o) {
    this.id = o.getId();
    this.userId = o.getUser().getId();
    this.shippingAddressId = o.getShippingAddress().getId();
    this.status = o.getStatus();
    this.totalPrice = o.getTotalPrice();
    this.createdAt = o.getCreatedAt();
    this.orderItems = o.getOrderItems().stream()
            .map(OrderItemResponse::new)
            .collect(Collectors.toList());
  }
}
