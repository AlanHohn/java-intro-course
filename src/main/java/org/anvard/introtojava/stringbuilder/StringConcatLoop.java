package org.anvard.introtojava.stringbuilder;

import java.math.BigInteger;
import java.security.SecureRandom;

public class StringConcatLoop {

	public static void main(String[] args) {
		SecureRandom random = new SecureRandom();
		String[] strings = new String[20];
		for (int i = 0; i < strings.length; i++) {
			strings[i] = new BigInteger(130, random).toString(32);
		}
		
		String output = "";
		for (int i = 0; i < strings.length; i++) {
			output = output + strings[i];
		}
		
		System.out.println(output);
	}
}
