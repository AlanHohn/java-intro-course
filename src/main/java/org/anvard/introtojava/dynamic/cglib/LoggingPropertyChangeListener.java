package org.anvard.introtojava.dynamic.cglib;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoggingPropertyChangeListener implements PropertyChangeListener {
	
	private static final Log LOG = LogFactory.getLog(LoggingPropertyChangeListener.class);

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		LOG.info("Property change: " + evt.getPropertyName() + "; Old value: " + evt.getOldValue() + ", New value: "
				+ evt.getNewValue());
	}
}
