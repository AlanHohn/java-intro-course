package org.anvard.introtojava.generics;

import java.util.List;

public interface NumberSmasher<T extends Number> {
  T smash(List<T> numbers);
}
