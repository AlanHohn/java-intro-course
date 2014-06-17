package org.anvard.introtojava.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class SocketExample {

  private Socket client;
  private DataInputStream dis;
  private DataOutputStream dos;
  private boolean connected;
  
  public void connect() {
    if (!connected) {
      try {
        client = new Socket("localhost", 12345);
        dis = new DataInputStream(
            client.getInputStream());
        dos = new DataOutputStream(
            client.getOutputStream());
        connected = true;
      } catch (IOException e) {
        System.out.println("Failed to connect");
        connected = false;
      }
    }
  }
  
  public void sendString(String s) {
    if (!connected) {
      connect();
    }
    if (connected) {
      try {
        dos.writeUTF(s);
      } catch (IOException e) {
        connected = false;
      }
    }
  }
  
  public String readString() {
    if (!connected) {
      connect();
    }
    if (connected) {
      try {
        return dis.readUTF();
      } catch (IOException e) {
        connected = false;
      }
    }
    return null;
  }
  
  public void sendObject(Serializable s) {
    if (!connected) {
      connect();
    }
    if (connected) {
      try {
        ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
        oos.writeObject(s);
      } catch (IOException e) {
        connected = false;
      }
    }
  }
  
  public void close() {
    this.connected = false;
    if (null != client) {
      try {
        client.close();
      } catch (IOException ignored) {
      }
    }
  }
  
  public static void main(String[] args) {
    SocketExample ex = new SocketExample();
    ex.sendString("Duke");
    System.out.println(ex.readString());
    ex.close();
  }
}
