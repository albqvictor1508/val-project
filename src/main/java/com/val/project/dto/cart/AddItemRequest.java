
package com.val.project.dto.cart;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddItemRequest {
    @NotNull
    private Long productId;

    @Min(1)
    @NotNull
    private Integer quantity;
}
