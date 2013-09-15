package org.anvard.introtojava.syntax;

// Classes in other packages that we need
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HelloName {
	
	public static void main(String[] args) 
			throws Exception {
		System.out.print("What is your name? ");
		// 1 statement, 3 objects
		String name = new BufferedReader(
				new InputStreamReader(System.in)).readLine();
		if (name.length() > 10) {
			System.out.println("You have a long name.");
		}
		// Smart concatenation, but no operator overloading
		System.out.println("Hello, " + name);
	}
}
