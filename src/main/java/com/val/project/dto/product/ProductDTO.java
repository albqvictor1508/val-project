package com.val.project.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDTO {
  @NotNull
  @NotBlank
  private String name;
  @NotNull
  @NotBlank
  private String description;
  @NotNull
  private Double price;
}
