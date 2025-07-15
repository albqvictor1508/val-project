package com.val.project.dto.order;

import com.val.project.entity.Order;
import com.val.project.types.OrderStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
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

  public OrderResponse(Order o) {
    this.id = o.getId();
    this.userId = o.getUser().getId();
    this.shippingAddressId = o.getShippingAddress().getId();
    this.status = o.getStatus();
    this.totalPrice = o.getTotalPrice();
    this.createdAt = o.getCreatedAt();
  }
}
