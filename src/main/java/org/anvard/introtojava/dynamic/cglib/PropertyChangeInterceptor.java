package org.anvard.introtojava.dynamic.cglib;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class PropertyChangeInterceptor implements MethodInterceptor {

	private PropertyChangeSupport pcs;

	public void setTarget(Object target) {
		this.pcs = new PropertyChangeSupport(target);
	}

	private void addPropertyChangeListener(PropertyChangeListener listener) {
		if (null != pcs) {
			pcs.addPropertyChangeListener(listener);
		}
	}

	private void removePropertyChangeListener(PropertyChangeListener listener) {
		if (null != pcs) {
			pcs.removePropertyChangeListener(listener);
		}
	}

	private void firePropertyChange(String propName, Object oldValue, Object newValue) {
		if (null != pcs) {
			pcs.firePropertyChange(propName, oldValue, newValue);
		}
	}

	private Object tryForGetter(String setterName, Object target) {
		String getterName = "get" + setterName.substring(3);
		try {
			return target.getClass().getMethod(getterName, new Class<?>[] {}).invoke(target, (Object[]) null);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			return null;
		}
	}

	@Override
	public Object intercept(Object target, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		Object targetReturn = null;

		// See if this method call should stop here
		if (method.getName().equals("addPropertyChangeListener")) {
			Class<?>[] paramTypes = method.getParameterTypes();
			if (paramTypes.length == 1 && paramTypes[0].equals(PropertyChangeListener.class)) {
				addPropertyChangeListener((PropertyChangeListener) args[0]);
				return null;
			}
		} else if (method.getName().equals("removePropertyChangeListener")) {
			Class<?>[] paramTypes = method.getParameterTypes();
			if (paramTypes.length == 1 && paramTypes[0].equals(PropertyChangeListener.class)) {
				removePropertyChangeListener((PropertyChangeListener) args[0]);
				return null;
			}
		}

		// Otherwise pass through to the real object
		Object oldValue = null;
		String name = method.getName();
		boolean isSetter = (name.startsWith("set") && args.length == 1 && method.getReturnType() == Void.TYPE);
		if (isSetter) {
			oldValue = tryForGetter(name, target);
		}
		if (!Modifier.isAbstract(method.getModifiers())) {
			targetReturn = proxy.invokeSuper(target, args);
		}
		if (isSetter) {
			String propName = Character.toLowerCase(name.charAt(3)) + name.substring(4);
			firePropertyChange(propName, oldValue, args[0]);
		}
		return targetReturn;
	}

}
