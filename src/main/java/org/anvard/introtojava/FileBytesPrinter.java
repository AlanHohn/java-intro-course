package org.anvard.introtojava;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileBytesPrinter {

    public static void main(String[] args) {
	File f = new File("input.txt");
	try (BufferedInputStream bis = new BufferedInputStream(
		new FileInputStream(f))) {
	    int nextByte = bis.read();
	    while (nextByte != -1) {
		System.out.print(nextByte);
		nextByte = bis.read();
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
