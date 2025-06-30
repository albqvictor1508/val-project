package com.val.project.dto.category;

import com.val.project.entity.Category;

import lombok.Getter;

@Getter
public class CategoryResponse {
  private final Long id;
  private final String name;
  private final String slug;

  public CategoryResponse(Category c) {
    this.id = c.getId();
    this.name = c.getName();
    this.slug = c.getSlug();
  }
}
