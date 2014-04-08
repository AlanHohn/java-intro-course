package org.anvard.introtojava.resources;

public class ResourceLoader {

  public static void main(String[] args) {
    // Returns URL for org/anvard/introtojava/resources/file.xml
    ResourceLoader.class.getResource("file.xml");
    // Loads file.xml from the root of a classpath location
    ResourceLoader.class.getResource("/file.xml");
    // Same as the first statement
    ResourceLoader.class
        .getResource("/org/anvard/introtojava/resources/file.xml");
  }

}
