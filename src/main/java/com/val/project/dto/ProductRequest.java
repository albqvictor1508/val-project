package com.val.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ProductRequest {
  @NotBlank
  @NotNull
  private String name;
  @NotBlank
  @NotNull
  private String description;
  @NotBlank
  @NotNull
  private Double price;
  @NotBlank
  @NotNull
  private Long categoryId;
}
