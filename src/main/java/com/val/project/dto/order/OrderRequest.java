package com.val.project.dto.order;

import com.val.project.entity.Address;
import com.val.project.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
  private User user;
  private Address shippingAddress;
}
