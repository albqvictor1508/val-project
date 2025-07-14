package com.val.project.dto.cartItems;

import com.val.project.entity.CartItem;

import lombok.Getter;

@Getter
public class CartItemResponse {
  private Long id;
  private Long productId;
  private Double unitPrice;
  private Integer quantity;

  public CartItemResponse(CartItem cartItem) {
    id = cartItem.getId();
    productId = cartItem.getProduct().getId();
    unitPrice = cartItem.getUnitPrice();
    quantity = cartItem.getQuantity();
  }
}
