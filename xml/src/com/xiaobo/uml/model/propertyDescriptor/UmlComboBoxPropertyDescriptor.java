package com.xiaobo.uml.model.propertyDescriptor;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.ComboBoxLabelProvider;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class UmlComboBoxPropertyDescriptor extends UmlPropertyDescriptor {

	private static final long serialVersionUID = 1L;

	private String[] labels;

	public UmlComboBoxPropertyDescriptor(Object id, String displayName,
			String[] labelsArray) {
		super(id, displayName);
		labels = labelsArray;
	}

	public CellEditor createPropertyEditor(Composite parent) {
		CellEditor editor = new ComboBoxCellEditor(parent, labels,
				SWT.READ_ONLY);
		if (getValidator() != null) {
			editor.setValidator(getValidator());
		}
		return editor;
	}

	public ILabelProvider getLabelProvider() {
		if (isLabelProviderSet()) {
			return super.getLabelProvider();
		} else {
			return new ComboBoxLabelProvider(labels);
		}
	}
}
