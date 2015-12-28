package org.anvard.introtojava.dynamic.cglib;

import java.beans.PropertyChangeListener;

public interface Observable {

	void addPropertyChangeListener(PropertyChangeListener listener);
	
	void removePropertyChangeListener(PropertyChangeListener listener);
	
}
