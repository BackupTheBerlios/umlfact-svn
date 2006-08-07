package com.xiaobo.uml.model;

import org.eclipse.jface.viewers.ICellEditorValidator;

import com.xiaobo.uml.model.propertyDescriptor.UmlComboBoxPropertyDescriptor;
import com.xiaobo.uml.model.propertyDescriptor.UmlTextPropertyDescriptor;

/**
 * 
 * @author xiaobo
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class Aggregation extends ConnectionModel {

	private static final long serialVersionUID = 1L;

	private static final String SOURCESTRING_PROP = "sourcestring";

	private static final String ISCOMPOSITION_PROP = "iscomposition";

	private UmlTextPropertyDescriptor sourceStringPropertyDescriptor;

	private UmlComboBoxPropertyDescriptor isCompositionPropertyDecriptor;

	private String sourceString;

	private boolean isComposition;

	public Aggregation(boolean isComposition, String sourceString) {
		setComposition(isComposition);
		setSourceString(sourceString);
	}

	protected void createPropertyDescriptors() {
		super.createPropertyDescriptors();
		sourceStringPropertyDescriptor = new UmlTextPropertyDescriptor(
				SOURCESTRING_PROP, "Source string");
		addPropertyDescriptor(sourceStringPropertyDescriptor);
		isCompositionPropertyDecriptor = new UmlComboBoxPropertyDescriptor(
				ISCOMPOSITION_PROP, "isComposition", new String[] { "true",
						"false" });
		addPropertyDescriptor(isCompositionPropertyDecriptor);
	}

	public boolean isComposition() {
		return isComposition;
	}

	public void setComposition(boolean isComposition) {
		this.isComposition = isComposition;
		firePropertyChange(ISCOMPOSITION_PROP);
	}

	public String getSourceString() {
		return sourceString;
	}

	public void setSourceString(String sourceString) {
		this.sourceString = sourceString;
		firePropertyChange(SOURCESTRING_PROP);
	}

	public Object getPropertyValue(Object id) {
		if (SOURCESTRING_PROP.equals((id))) {
			return getSourceString();
		} else if (ISCOMPOSITION_PROP.equals(id)) {
			if (isComposition()) {
				return new Integer(0);
			}
			return new Integer(1);
		}
		return super.getPropertyValue(id);
	}

	public boolean isPropertySet(Object id) {
		if (id.equals(SOURCESTRING_PROP)) {
			return true;
		} else if (id.equals(ISCOMPOSITION_PROP)) {
			return true;
		}
		return super.isPropertySet(id);
	}

	public void resetPropertyValue(Object id) {
		super.resetPropertyValue(id);
	}

	public void setPropertyValue(Object id, Object value) {
		super.setPropertyValue(id, value);
		if (SOURCESTRING_PROP.equals((id))) {
			setSourceString((String) value);
		} else if (ISCOMPOSITION_PROP.equals(id)) {
			if (value.equals(new Integer(0))) {
				setComposition(true);
			} else {
				setComposition(false);
			}
		}
	}

	public void setNameValidator(ICellEditorValidator validator) {
		sourceStringPropertyDescriptor.setValidator(validator);
	}
}
