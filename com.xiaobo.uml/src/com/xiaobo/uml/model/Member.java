package com.xiaobo.uml.model;

import com.xiaobo.uml.model.propertyDescriptor.UmlComboBoxPropertyDescriptor;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public abstract class Member extends NamedElement {

	private boolean isStatic;

	public static final String ISSTATIC_PROP = "static";

	private UmlComboBoxPropertyDescriptor isStaticDescriptor;

	public Member() {
		setName("member");
	}

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
		firePropertyChange(ISSTATIC_PROP);
	}

	protected void createPropertyDescriptors() {
		super.createPropertyDescriptors();
		isStaticDescriptor = new UmlComboBoxPropertyDescriptor(ISSTATIC_PROP,
				"is static", new String[] { "true", "false" });
		addPropertyDescriptor(isStaticDescriptor);
	}

	public Object getPropertyValue(Object id) {
		if (ISSTATIC_PROP.equals(id)) {
			if (isStatic()) {
				return new Integer(0);
			}
			return new Integer(1);
		}
		return super.getPropertyValue(id);
	}

	public boolean isPropertySet(Object id) {
		if (id.equals(ISSTATIC_PROP)) {
			return true;
		}
		return super.isPropertySet(id);
	}

	public void resetPropertyValue(Object id) {
		super.resetPropertyValue(id);
	}

	public void setPropertyValue(Object id, Object value) {
		super.setPropertyValue(id, value);
		if (ISSTATIC_PROP.equals(id)) {
			if (value.equals(new Integer(0))) {
				setStatic(true);
			} else {
				setStatic(false);
			}
		}
	}

}
