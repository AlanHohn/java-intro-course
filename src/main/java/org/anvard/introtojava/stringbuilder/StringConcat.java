package org.anvard.introtojava.stringbuilder;

import java.math.BigInteger;
import java.security.SecureRandom;

public class StringConcat {

	public static void main(String[] args) {
		SecureRandom random = new SecureRandom();
		String s1 = new BigInteger(120, random).toString(32);
		String s2 = new BigInteger(120, random).toString(32);
		String s3 = new BigInteger(120, random).toString(32);
		String s4 = new BigInteger(120, random).toString(32);
		String s5 = new BigInteger(120, random).toString(32);
		
		String output = s1 + s2 + s3 + s4 + s5;
		System.out.println(output);
	}
}
