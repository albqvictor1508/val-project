package com.val.project.dto.order;

import com.val.project.entity.Order;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
  private Long userId;
  private Long shippingAddressId;

  public OrderRequest(Order o) {
    this.userId = o.getUser().getId();
    this.shippingAddressId = o.getShippingAddress().getId();
  }
}
