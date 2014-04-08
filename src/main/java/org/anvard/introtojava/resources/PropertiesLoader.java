package org.anvard.introtojava.resources;

import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {

  public static void main(String[] args) throws IOException {
    Properties p = new Properties();
    p.load(PropertiesLoader.class.getResourceAsStream("sample.properties"));
    System.out.println(p.getProperty("abc")); // 123
    System.out.println(p.getProperty("def")); // My Long String
    System.out.println(p.getProperty("xyz", "default")); // default
    System.out.println(p.getProperty("xyz")); // null
  }

}
