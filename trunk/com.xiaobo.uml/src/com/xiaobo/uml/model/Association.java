package com.xiaobo.uml.model;

import org.eclipse.jface.viewers.ICellEditorValidator;

import com.xiaobo.uml.model.propertyDescriptor.UmlComboBoxPropertyDescriptor;
import com.xiaobo.uml.model.propertyDescriptor.UmlTextPropertyDescriptor;

/**
 * 
 * @author xiaobo. Created on Jun 18, 2006.
 * 
 * Copyright 2006 by Xiaobo Sun. All rights reserved.
 * 
 */
public class Association extends ConnectionModel {

	private static final long serialVersionUID = 1L;

	private static final String SOURCESTRING_PROP = "sourcestring";

	private static final String TARGETSTRING_PROP = "targetstring";

	private static final String SOURCENAVIGATION_PROP = "sourcenavigation";

	private static final String TARGETNAVIGATION_PROP = "targetnavigation";

	private String sourceString;

	private String targetString;

	private boolean sourceNavigation;

	private boolean targetNavigation;

	private UmlTextPropertyDescriptor sourceStringPropertyDescriptor;

	private UmlTextPropertyDescriptor targetStringPropertyDescriptor;

	private UmlComboBoxPropertyDescriptor sourceNavigationPropertyDecriptor;

	private UmlComboBoxPropertyDescriptor targetNavigationPropertyDecriptor;

	public Association(String sourceString, String targetString,
			boolean sourceNavigation, boolean targetNavigation) {
		setSourceString(sourceString);
		setTargetString(targetString);
		setSourceNavigation(sourceNavigation);
		setTargetNavigation(targetNavigation);
	}

	public String getSourceString() {
		return sourceString;
	}

	public void setSourceString(String sourceString) {
		this.sourceString = sourceString;
		firePropertyChange(SOURCESTRING_PROP);
	}

	public String getTargetString() {
		return targetString;
	}

	public void setTargetString(String targetString) {
		this.targetString = targetString;
		firePropertyChange(TARGETSTRING_PROP);
	}

	public boolean isSourceNavigation() {
		return sourceNavigation;
	}

	public void setSourceNavigation(boolean sourceNavigation) {
		this.sourceNavigation = sourceNavigation;
		firePropertyChange(SOURCENAVIGATION_PROP);
	}

	public boolean isTargetNavigation() {
		return targetNavigation;
	}

	public void setTargetNavigation(boolean targetNavigation) {
		this.targetNavigation = targetNavigation;
		firePropertyChange(TARGETNAVIGATION_PROP);
	}

	protected void createPropertyDescriptors() {
		super.createPropertyDescriptors();
		sourceStringPropertyDescriptor = new UmlTextPropertyDescriptor(
				SOURCESTRING_PROP, "Source string");
		addPropertyDescriptor(sourceStringPropertyDescriptor);
		targetStringPropertyDescriptor = new UmlTextPropertyDescriptor(
				TARGETSTRING_PROP, "Target string");
		addPropertyDescriptor(targetStringPropertyDescriptor);
		sourceNavigationPropertyDecriptor = new UmlComboBoxPropertyDescriptor(
				SOURCENAVIGATION_PROP, "source navigation", new String[] {
						"true", "false" });
		addPropertyDescriptor(sourceNavigationPropertyDecriptor);
		targetNavigationPropertyDecriptor = new UmlComboBoxPropertyDescriptor(
				TARGETNAVIGATION_PROP, "target navigation", new String[] {
						"true", "false" });
		addPropertyDescriptor(targetNavigationPropertyDecriptor);
	}

	public Object getPropertyValue(Object id) {
		if (SOURCESTRING_PROP.equals((id))) {
			return getSourceString();
		} else if (SOURCENAVIGATION_PROP.equals(id)) {
			if (isSourceNavigation()) {
				return new Integer(0);
			}
			return new Integer(1);
		} else if (TARGETSTRING_PROP.equals((id))) {
			return getTargetString();
		} else if (TARGETNAVIGATION_PROP.equals(id)) {
			if (isTargetNavigation()) {
				return new Integer(0);
			}
			return new Integer(1);
		}
		return super.getPropertyValue(id);
	}

	public boolean isPropertySet(Object id) {
		if (id.equals(SOURCESTRING_PROP)) {
			return true;
		} else if (id.equals(SOURCENAVIGATION_PROP)) {
			return true;
		} else if (id.equals(TARGETSTRING_PROP)) {
			return true;
		} else if (id.equals(TARGETNAVIGATION_PROP)) {
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
		} else if (SOURCENAVIGATION_PROP.equals(id)) {
			if (value.equals(new Integer(0))) {
				setSourceNavigation(true);
			} else {
				setSourceNavigation(false);
			}
		} else if (TARGETSTRING_PROP.equals((id))) {
			setTargetString((String) value);
		} else if (TARGETNAVIGATION_PROP.equals(id)) {
			if (value.equals(new Integer(0))) {
				setTargetNavigation(true);
			} else {
				setTargetNavigation(false);
			}
		}
	}

	public void setNameValidator(ICellEditorValidator validator) {
		sourceStringPropertyDescriptor.setValidator(validator);
		targetStringPropertyDescriptor.setValidator(validator);
	}
}
