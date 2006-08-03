package com.xiaobo.uml.model;

import com.xiaobo.uml.model.propertyDescriptor.UmlComboBoxPropertyDescriptor;

/**
 * 
 * @author xiaobo
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class Method extends Member {

	private static final long serialVersionUID = 1L;

	private boolean isAbstract;

	public static final String ISABSTRACT_PROP = "abstract";

	private UmlComboBoxPropertyDescriptor isAbstractDescriptor;

	public Method() {
		setName("+method(paramtype):returntype");
		setDescription("method");
	}

	public boolean isAbstract() {
		return isAbstract;
	}

	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
		firePropertyChange(ISABSTRACT_PROP);
	}

	protected void createPropertyDescriptors() {
		super.createPropertyDescriptors();
		isAbstractDescriptor = new UmlComboBoxPropertyDescriptor(
				ISABSTRACT_PROP, "is abstract",
				new String[] { "true", "false" });
		addPropertyDescriptor(isAbstractDescriptor);
	}

	public Object getPropertyValue(Object id) {
		if (ISABSTRACT_PROP.equals(id)) {
			if (isAbstract()) {
				return new Integer(0);
			}
			return new Integer(1);
		}
		return super.getPropertyValue(id);
	}

	public boolean isPropertySet(Object id) {
		if (id.equals(ISABSTRACT_PROP)) {
			return true;
		}
		return super.isPropertySet(id);
	}

	public void resetPropertyValue(Object id) {
		super.resetPropertyValue(id);
	}

	public void setPropertyValue(Object id, Object value) {
		super.setPropertyValue(id, value);
		if (ISABSTRACT_PROP.equals(id)) {
			if (value.equals(new Integer(0))) {
				setAbstract(true);
			} else {
				setAbstract(false);
			}
		}
	}
}
