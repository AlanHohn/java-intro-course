package org.anvard.introtojava.generics;

import java.util.List;

public interface ThingSmasher {
  <T> T smash(List<T> things);
}
