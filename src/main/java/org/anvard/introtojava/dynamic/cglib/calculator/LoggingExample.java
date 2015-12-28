package org.anvard.introtojava.dynamic.cglib.calculator;

import net.sf.cglib.proxy.Enhancer;

public class LoggingExample {

	public static void main(String[] args) {
		LoggingInterceptor interceptor = new LoggingInterceptor();

		Enhancer e = new Enhancer();
		e.setSuperclass(Calculator.class);
		e.setCallback(interceptor);

		Calculator real = new Calculator();
		Calculator proxy = (Calculator) e.create();

		/* Will not be logged */
		System.out.println("2 + 2 = " + real.add(2, 2));
		/* Will be logged */
		System.out.println("2 + 2 = " + proxy.add(2, 2));

	}
}
