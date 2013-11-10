package org.anvard.introtojava.spring;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.StaticApplicationContext;

public class StaticContext {

	public class BeanContext {
		private String name;
		private Class<?> beanClass;
		private ConstructorArgumentValues args;
		private MutablePropertyValues props;
		
		private BeanContext(String name, Class<?> beanClass) {
			this.name = name;
			this.beanClass = beanClass;
			this.args = new ConstructorArgumentValues();
			this.props = new MutablePropertyValues();
		}
		public BeanContext arg(Object arg) {
			args.addGenericArgumentValue(arg);
			return this;
		}
		public BeanContext arg(int index, Object arg) {
			args.addIndexedArgumentValue(index, arg);
			return this;
		}
		public BeanContext prop(String name, Object value) {
			props.add(name, value);
			return this;
		}
		public BeanContext ref(String name, String beanRef) {
			props.add(name, new RuntimeBeanReference(beanRef));
			return this;
		}
		public void build() {
			RootBeanDefinition def = new RootBeanDefinition(beanClass, args, props);
			ctx.registerBeanDefinition(name, def);
		}
	}
	
	private StaticApplicationContext ctx;
	
	private StaticContext() {
		this.ctx = new StaticApplicationContext();
	}
	
	public static StaticContext create() {
		return new StaticContext();
	}
	
	public ApplicationContext build() {
		ctx.refresh();
		return ctx;
	}
	
	public BeanContext bean(String name, Class<?> beanClass) {
		return new BeanContext(name, beanClass);
	}
	
}
