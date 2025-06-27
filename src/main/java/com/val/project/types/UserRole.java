package com.val.project.types;

public enum UserRole {
  USER("user"),
  MANAGER("manager");

  private String label;

  private UserRole(String label) {
    this.label = label;
  }
}
