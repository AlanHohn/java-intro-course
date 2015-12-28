package org.anvard.introtojava.dynamic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoggingInvocationHandler implements InvocationHandler {
	private static final Log LOG = LogFactory.getLog(LoggingInvocationHandler.class);
	private final Object target;

	public LoggingInvocationHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		LOG.info("Starting call to: " + method.getName());
		Object returnObject = method.invoke(target, args);
		LOG.info("Completed call to: " + method.getName());
		return returnObject;
	}
}
