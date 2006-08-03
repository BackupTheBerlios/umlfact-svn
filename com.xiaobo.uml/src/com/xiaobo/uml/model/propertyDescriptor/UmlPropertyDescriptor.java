package com.xiaobo.uml.model.propertyDescriptor;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class UmlPropertyDescriptor implements IUmlPropertyDescriptor {

	private static final long serialVersionUID = 1L;

	private Object id;

	private String display;

	private String category = null;

	private String description = null;

	private Object helpIds;

	private String[] filterFlags;

	private ILabelProvider labelProvider = null;

	private ICellEditorValidator validator;

	private boolean incompatible = false;

	public UmlPropertyDescriptor(Object id, String displayName) {
		Assert.isNotNull(id);
		Assert.isNotNull(displayName);
		this.id = id;
		this.display = displayName;
	}

	public CellEditor createPropertyEditor(Composite parent) {
		return null;
	}

	protected boolean getAlwaysIncompatible() {
		return incompatible;
	}

	public String getCategory() {
		return category;
	}

	public String getDescription() {
		return description;
	}

	public String getDisplayName() {
		return display;
	}

	public String[] getFilterFlags() {
		return filterFlags;
	}

	public Object getHelpContextIds() {
		return helpIds;
	}

	public Object getId() {
		return id;
	}

	public ILabelProvider getLabelProvider() {
		if (labelProvider != null) {
			return labelProvider;
		} else {
			return new LabelProvider();
		}
	}

	protected ICellEditorValidator getValidator() {
		return validator;
	}

	public boolean isLabelProviderSet() {
		return labelProvider != null;
	}

	public boolean isCompatibleWith(IPropertyDescriptor anotherProperty) {
		if (getAlwaysIncompatible()) {
			return false;
		}

		// Compare id
		Object id1 = getId();
		Object id2 = anotherProperty.getId();
		if (!id1.equals(id2)) {
			return false;
		}

		// Compare Category (may be null)
		if (getCategory() == null) {
			if (anotherProperty.getCategory() != null) {
				return false;
			}
		} else {
			if (!getCategory().equals(anotherProperty.getCategory())) {
				return false;
			}
		}

		return true;
	}

	public void setAlwaysIncompatible(boolean flag) {
		incompatible = flag;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setFilterFlags(String value[]) {
		filterFlags = value;
	}

	public void setHelpContextIds(Object contextIds) {
		helpIds = contextIds;
	}

	public void setLabelProvider(ILabelProvider provider) {
		labelProvider = provider;
	}

	public void setValidator(ICellEditorValidator validator) {
		this.validator = validator;
	}

}
