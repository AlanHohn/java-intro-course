package org.anvard.introtojava.generics;

import java.util.ArrayList;
import java.util.List;


public class PreGenericList {

  public static <T extends Number> int sum(List<T> l) {
    int sum = 0;
    for (Object o: l) {
      sum += (Integer)o; // Cast
    }
    return sum;
  }
  public static void main(String args[]) {
    List l = new ArrayList();
    l.add(5); l.add(6); l.add(7);
    System.out.println("Sum: " + sum(l));
    l.add("Whoops");
    System.out.println("Sum: " + sum(l));
  }
}
