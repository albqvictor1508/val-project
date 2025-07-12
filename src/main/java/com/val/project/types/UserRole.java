package com.val.project.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
  USER("user"),
  MANAGER("manager");

  private String label;
}
