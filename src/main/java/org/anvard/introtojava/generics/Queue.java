package org.anvard.introtojava.generics;

import java.util.LinkedList;
import java.util.List;

public class Queue<T extends Number> {

  private List<T> content;
  
  public Queue() {
    this.content = new LinkedList<>();
  }
  
  public void putItem(T item) {
    content.add(item);
  }
  
  public T takeItem(T item) {
    return content.isEmpty() ? null : content.remove(0);
  }
  
  public static void main(String[] args) {
    new Queue();
  }
  
}
