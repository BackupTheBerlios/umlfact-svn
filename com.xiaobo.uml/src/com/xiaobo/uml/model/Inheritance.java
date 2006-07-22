package com.xiaobo.uml.model;

import com.xiaobo.uml.model.propertyDescriptor.UmlComboBoxPropertyDescriptor;

/**
 * 
 * @author xiaobo
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class Inheritance extends ConnectionModel {

	private static final long serialVersionUID = 1L;

	private static final String ISIMPL_PROP = "isImplement";

	private UmlComboBoxPropertyDescriptor isImplPropertyDecriptor;

	private boolean isImpl;

	public Inheritance(boolean impl) {
		setImpl(impl);
	}

	protected void createPropertyDescriptors() {
		super.createPropertyDescriptors();
		isImplPropertyDecriptor = new UmlComboBoxPropertyDescriptor(
				ISIMPL_PROP, "isImplements", new String[] { "true", "false" });
		addPropertyDescriptor(isImplPropertyDecriptor);
	}

	public boolean isImpl() {
		return isImpl;
	}

	public void setImpl(boolean impl) {
		this.isImpl = impl;
		firePropertyChange(ISIMPL_PROP);
	}

	public Object getPropertyValue(Object id) {
		if (ISIMPL_PROP.equals(id)) {
			if (isImpl()) {
				return new Integer(0);
			}
			return new Integer(1);
		}
		return super.getPropertyValue(id);
	}

	public boolean isPropertySet(Object id) {
		if (id.equals(ISIMPL_PROP)) {
			return true;
		}
		return super.isPropertySet(id);
	}

	public void resetPropertyValue(Object id) {
		super.resetPropertyValue(id);
	}

	public void setPropertyValue(Object id, Object value) {
		super.setPropertyValue(id, value);
		if (ISIMPL_PROP.equals(id)) {
			if (value.equals(new Integer(0))) {
				setImpl(true);
			} else {
				setImpl(false);
			}
		}
	}
}
