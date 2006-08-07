package com.xiaobo.uml.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public abstract class UmlPropertySource implements IPropertySource {

	private transient PropertyChangeSupport pcsDelegate;

	public synchronized void addPropertyChangeListener(PropertyChangeListener l) {
		if (l == null) {
			throw new IllegalArgumentException();
		}
		getPcsDelegate().addPropertyChangeListener(l);
	}

	protected void firePropertyChange(String property) {
		if (getPcsDelegate().hasListeners(property)) {
			getPcsDelegate().firePropertyChange(property, null, null);
		}
	}

	public synchronized void removePropertyChangeListener(
			PropertyChangeListener l) {
		if (l != null) {
			getPcsDelegate().removePropertyChangeListener(l);
		}
	}

	private PropertyChangeSupport getPcsDelegate() {
		if (pcsDelegate == null) {
			pcsDelegate = new PropertyChangeSupport(this);
		}
		return pcsDelegate;
	}

	public IPropertyDescriptor[] getPropertyDescriptors() {
		return null;
	}

	public Object getEditableValue() {
		return this;
	}

	/**
	 * the underlying 4 methods for the property sheet(page).
	 */
	public Object getPropertyValue(Object id) {
		return null;
	}

	public boolean isPropertySet(Object id) {
		return false;
	}

	public void setPropertyValue(Object id, Object value) {
	}

	public void resetPropertyValue(Object id) {
	}

}
