package com.xiaobo.uml.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.xiaobo.uml.model.propertyDescriptor.IUmlPropertyDescriptor;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlElement extends UmlPropertySource implements IUmlElement,
		Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * for the property sheet.
	 */
	private List propertyDescriptors = new ArrayList();

	public UmlElement() {
		createPropertyDescriptors();
	}

	protected void createPropertyDescriptors() {
	}

	public IPropertyDescriptor[] getPropertyDescriptors() {
		IUmlPropertyDescriptor[] pD = new IUmlPropertyDescriptor[propertyDescriptors
				.size()];
		for (int i = 0; i < propertyDescriptors.size(); i++) {
			pD[i] = (IUmlPropertyDescriptor) propertyDescriptors.get(i);
		}
		return pD;
	}

	protected void addPropertyDescriptor(IPropertyDescriptor descriptor) {
		propertyDescriptors.add(descriptor);
	}
}
