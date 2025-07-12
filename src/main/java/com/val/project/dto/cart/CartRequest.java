package com.val.project.dto.cart;

import jakarta.validation.Valid;
import lombok.Getter;

@Getter
public class CartRequest {
  @Valid
  private Long userId;
  @Valid
  private String sessionId;
}
