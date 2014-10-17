package org.anvard.introtojava.swing;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;

import javax.swing.JFileChooser;

public class FileChooser {

  public static void main(String[] args) {
    JFileChooser fc = new JFileChooser();
    fc.setMultiSelectionEnabled(true);
    fc.showOpenDialog(null);
    for (File f: fc.getSelectedFiles()) System.out.println(f.getAbsolutePath());
    FileDialog fd = new FileDialog((Frame)null);
    fd.setMultipleMode(true);
    fd.setVisible(true);
    for (File f: fd.getFiles()) System.out.println(f.getAbsolutePath());
  }
}
