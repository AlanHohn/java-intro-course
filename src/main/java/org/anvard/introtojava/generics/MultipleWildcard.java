package org.anvard.introtojava.generics;

import java.util.ArrayList;
import java.util.List;

public class MultipleWildcard {

  public class Queue1<T> {
    public List<T> content;
    public void addThese(List<T> items) {
      content.addAll(items);
    }
  }
  
  public class Queue2<T> {
    public List<T> content;
    public void addThese(List<? extends T> items) {
      content.addAll(items);
    }
  }
  
  public class Queue3<T> {
    public List<T> content;
    public <R extends T> void addThese(List<R> items) {
      content.addAll(items);
    }
  }
  
  public static void main(String[] args) {
    Queue1<Number> q1 = new MultipleWildcard().new Queue1<>();
    Queue2<Number> q2 = new MultipleWildcard().new Queue2<>();
    List<Integer> mylist = new ArrayList<>();
    //q1.addThese(mylist);
    q2.addThese(mylist);
  }
}
