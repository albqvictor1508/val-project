package com.val.project.dto.product;

import java.time.LocalDateTime;

import com.val.project.entity.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ProductResponse {
  @NotBlank
  @NotNull
  private String name;
  @NotBlank
  @NotNull
  private String description;
  @NotNull
  private Double price;
  @NotNull
  private Long categoryId;
  @NotNull
  private LocalDateTime createdAt;
  @NotNull
  private LocalDateTime updatedAt;

  public ProductResponse(Product p) {
    this.name = p.getName();
    this.description = p.getDescription();
    this.price = p.getPrice();
    this.categoryId = p.getCategory().getId();
    this.createdAt = p.getCreatedAt();
    this.updatedAt = p.getUpdatedAt();
  }
}
