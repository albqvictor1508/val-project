package com.val.project.dto.cart;

import java.util.List;

import com.val.project.dto.cartItems.CartItemResponse;
import com.val.project.types.CartStatusEnum;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Getter
public class AddItemResponse {
  private Long id;
  private Long userId;
  private Long total;
  @Enumerated(EnumType.STRING)
  private CartStatusEnum status;
  private List<CartItemResponse> items;
}
