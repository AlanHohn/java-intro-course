package org.anvard.introtojava.network;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements Hello {
  
  public String sayHello(String name) {
      return "Hello, " + name + "!";
  }
      
  public static void main(String args[]) {
      
      try {
          Server server = new Server();
          Hello stub = (Hello) UnicastRemoteObject.exportObject(server, 0);
          Registry registry = LocateRegistry.getRegistry();
          registry.bind("Hello", stub);
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
}