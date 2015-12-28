package org.anvard.introtojava.dynamic.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyExample {

	public static void main(String[] args) throws Exception {
		Calculator real = new CalculatorImpl();
		InvocationHandler handler = new LoggingInvocationHandler(real);

		/* The long way */
		Class<?> proxyClass = Proxy.getProxyClass(ClassLoader.getSystemClassLoader(), Calculator.class);
		Constructor<?> ctor = proxyClass.getConstructor(InvocationHandler.class);
		Calculator proxy = (Calculator) ctor.newInstance(handler);

		/* The short way */
		Calculator secondProxy = (Calculator) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
				new Class[] { Calculator.class }, handler);

		/* Will not be logged */
		System.out.println("2 + 2 = " + real.add(2, 2));
		/* Will be logged */
		System.out.println("2 + 2 = " + proxy.add(2, 2));
		System.out.println("2 + 2 = " + secondProxy.add(2, 2));
	}
}
