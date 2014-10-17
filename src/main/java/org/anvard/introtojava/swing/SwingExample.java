package org.anvard.introtojava.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class SwingExample {

  public static void main(String[] args) throws Exception {
    UIManager.setLookAndFeel(
        UIManager.getSystemLookAndFeelClassName());
    
    // Components
    JFrame mainWindow = new JFrame("Example");
    JPanel entryPanel = new JPanel();
    final JTextField name = new JTextField(20);
    final JLabel output = new JLabel(" ");

    // Layout
    mainWindow.setLayout(new BorderLayout());
    entryPanel.add(new JLabel("Your name"));
    entryPanel.add(name);
    mainWindow.add(entryPanel, BorderLayout.NORTH);
    JButton helloButton = new JButton("Say Hello");
    mainWindow.add(helloButton, BorderLayout.CENTER);
    mainWindow.add(output, BorderLayout.SOUTH);
    
    // Behavior
    helloButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        output.setText("Hello, " + name.getText() + "!");
      }
    });
    
    mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainWindow.pack();
    mainWindow.setVisible(true);
    
  }
}
