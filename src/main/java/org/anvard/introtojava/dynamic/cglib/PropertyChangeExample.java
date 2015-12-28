package org.anvard.introtojava.dynamic.cglib;

public class PropertyChangeExample {

	public static void main(String[] args) {

		SampleBean regular = new SampleBean();
		SampleBean observableBean = ObservableBeanFactory.createObservableBean(SampleBean.class);

		((Observable) observableBean).addPropertyChangeListener(new LoggingPropertyChangeListener());

		/* Will not be observed */
		regular.setStringValue("abc");
		regular.setStringValue("def");
		regular.setIntValue(1);
		regular.setIntValue(2);

		/* Will be observed */
		observableBean.setStringValue("zyx");
		observableBean.setStringValue("wvu");
		observableBean.setIntValue(10);
		observableBean.setIntValue(20);

	}
}
