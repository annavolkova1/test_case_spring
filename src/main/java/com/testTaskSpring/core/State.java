package com.testTaskSpring.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class State {
  public static final String ENABLED = "enabled";
  public static final String DISABLED = "disabled";
}
