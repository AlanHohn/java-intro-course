package org.anvard.introtojava.stringbuilder;

import java.math.BigInteger;
import java.security.SecureRandom;

public class StringBuilderPlain {

	public static void main(String[] args) {
		SecureRandom random = new SecureRandom();
		String s1 = new BigInteger(120, random).toString(32);
		String s2 = new BigInteger(120, random).toString(32);
		String s3 = new BigInteger(120, random).toString(32);
		String s4 = new BigInteger(120, random).toString(32);
		String s5 = new BigInteger(120, random).toString(32);
		
		StringBuilder sb = new StringBuilder();
		sb.append(s1);
		sb.append(s2);
		sb.append(s3);
		sb.append(s4);
		sb.append(s5);
		System.out.println(sb.toString());
	}
}
