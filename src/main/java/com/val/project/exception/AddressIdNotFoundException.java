package com.val.project.exception;

public class AddressIdNotFoundException extends RuntimeException {
  private String message;

  public AddressIdNotFoundException(String message) {
    this.message = message;
  }
}
