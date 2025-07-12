package com.val.project.types;

import lombok.Getter;

@Getter
public enum CartStatusEnum {
  OPEN(""),
  FINALIZED("bsalsa");

  private final String label;

  private CartStatusEnum(final String label) {
    this.label = label;
  }
}
