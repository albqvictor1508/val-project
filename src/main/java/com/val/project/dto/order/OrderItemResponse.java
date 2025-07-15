package com.val.project.dto.order;

import com.val.project.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemResponse {
    private Long productId;
    private Integer quantity;
    private Double unitPrice;

    public OrderItemResponse(OrderItem orderItem) {
        this.productId = orderItem.getProduct().getId();
        this.quantity = orderItem.getQuantity();
        this.unitPrice = orderItem.getUnitPrice();
    }
}