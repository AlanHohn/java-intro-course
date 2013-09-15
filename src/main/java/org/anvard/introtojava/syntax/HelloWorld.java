// Java source code is organized into packages
package org.anvard.introtojava.syntax;

/* Java is an object oriented programming language.
 * All source code must be organized into "classes"
 * (discussed next time) */
public class HelloWorld {

	/* Note the C-like syntax. Like C, the first code
	 * run in a Java program is in a function called main */
	public static void main(String[] args) {
		/* The dot notation is used to find classes
		 * inside packages, and items inside classes. 
		 * This means "find the method called println in the
		 * object called 'out' in the class called System. */
		System.out.println("Hello world");
	}
}
