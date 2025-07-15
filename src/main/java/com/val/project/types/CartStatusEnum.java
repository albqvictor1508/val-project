package com.val.project.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CartStatusEnum {
  OPEN("open"),
  FINALIZED("finalized"),
  CLOSED("closed");

  private final String label;
}
