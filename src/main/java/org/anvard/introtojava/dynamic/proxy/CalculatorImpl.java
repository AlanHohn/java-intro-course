package org.anvard.introtojava.dynamic.proxy;

public class CalculatorImpl implements Calculator {

	@Override
	public int add(int left, int right) {
		return left+right;
	}

}
