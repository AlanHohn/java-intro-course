package org.anvard.introtojava.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketExample {

  private boolean running;
  private boolean connected;
  private ServerSocket socket;

  public ServerSocketExample() {
    this.running = true;
    this.connected = false;
    while (running) {
      while (!connected) {
        try {
          socket = new ServerSocket(12345);
          this.connected = true;
        } catch (IOException e) {
          System.out
              .println("Error occurred while connecting, waiting to retry");
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e1) {
          }
        }
      }
      while (connected) {
        try {
          final Socket s = socket.accept();
          Thread t = new Thread("Client bound to port: " + s.getLocalPort()) {
            public void run() {
              respond(s);
            }
          };
          t.setDaemon(true);
          t.start();
        } catch (IOException e) {
          System.out.println("Error occurred on server socket");
          this.connected = false;
        }
      }
    }
  }

  protected void respond(Socket s) {
    try {
      DataInputStream dis = new DataInputStream(s.getInputStream());
      DataOutputStream dos = new DataOutputStream(s.getOutputStream());
      while (true) {
        String name = dis.readUTF();
        dos.writeUTF("Hello, " + name + "!");
      }
    } catch (IOException e) {
      System.out.println("Lost client connection");
    }
  }

  protected void stop() {
    this.running = false;
    if (null != socket) {
      try {
        socket.close();
      } catch (IOException ignored) {
      }
    }
  }

  public static void main(String[] args) throws IOException {
    new ServerSocketExample();
  }
}
