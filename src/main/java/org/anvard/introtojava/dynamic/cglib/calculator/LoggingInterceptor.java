package org.anvard.introtojava.dynamic.cglib.calculator;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class LoggingInterceptor implements MethodInterceptor {
	private static final Log LOG = LogFactory.getLog(LoggingInterceptor.class);

	@Override
	public Object intercept(Object target, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		LOG.info("Starting call to: " + method.getName());
		Object returnObject = proxy.invokeSuper(target, args);
		LOG.info("Completed call to: " + method.getName());
		return returnObject;
	}

}
