package com.val.project.dto.cart;

import com.val.project.entity.Cart;

import jakarta.validation.Valid;
import lombok.Getter;

@Getter
public class CartResponse {
  @Valid
  private Long id;
  @Valid
  private Long userId;

  private String sessionId;

  public CartResponse(Cart cart) {
    id = cart.getId();
    userId = cart.getUser().getId();
    sessionId = cart.getSessionId();
  }
}
