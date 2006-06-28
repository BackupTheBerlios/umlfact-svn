package com.xiaobo.uml.model;

import org.eclipse.jface.viewers.ICellEditorValidator;

import com.xiaobo.uml.model.propertyDescriptor.UmlTextPropertyDescriptor;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public abstract class NamedElement extends UmlElement implements INamedElement {

	private static final long serialVersionUID = 1L;

	private static final String NAME_PROP = "name";

	private static final String DESCRIPTION_PROP = "description";

	private UmlTextPropertyDescriptor namePropertyDescriptor;

	private UmlTextPropertyDescriptor descriptionPropertyDescriptor;

	protected String name;

	private String description;

	public NamedElement() {
		setDescription("");
	}

	protected void createPropertyDescriptors() {
		super.createPropertyDescriptors();
		descriptionPropertyDescriptor = new UmlTextPropertyDescriptor(
				DESCRIPTION_PROP, "Description");
		addPropertyDescriptor(descriptionPropertyDescriptor);
		namePropertyDescriptor = new UmlTextPropertyDescriptor(NAME_PROP,
				"Name");
		addPropertyDescriptor(namePropertyDescriptor);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		firePropertyChange(NAME_PROP);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		firePropertyChange(DESCRIPTION_PROP);
	}

	public Object getPropertyValue(Object id) {
		if (NAME_PROP.equals((id))) {
			return getName();
		}
		if (DESCRIPTION_PROP.equals(id)) {
			return getDescription();
		}
		return super.getPropertyValue(id);
	}

	public boolean isPropertySet(Object id) {
		if (id.equals(NAME_PROP)) {
			return true;
		}
		if (id.equals(DESCRIPTION_PROP)) {
			return true;
		}
		return super.isPropertySet(id);
	}

	public void resetPropertyValue(Object id) {
		super.resetPropertyValue(id);
	}

	public void setPropertyValue(Object id, Object value) {
		super.setPropertyValue(id, value);
		if (NAME_PROP.equals((id))) {
			setName((String) value);
		} else if (DESCRIPTION_PROP.equals(id)) {
			setDescription((String) value);
		}
	}

	public void setNameValidator(ICellEditorValidator validator) {
		namePropertyDescriptor.setValidator(validator);
	}
}
