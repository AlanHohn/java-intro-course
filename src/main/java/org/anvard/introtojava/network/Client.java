package org.anvard.introtojava.network;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    public static void main(String[] args) {

        try {
            Registry registry = LocateRegistry.getRegistry("localhost");
            Hello stub = (Hello) registry.lookup("Hello");
            System.out.println(stub.sayHello("Duke"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}