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
public abstract class UmlElement extends UmlPropertySource implements
		IUmlElement, Serializable {
	/**
	 * for the property sheet.
	 */
	private List<IPropertyDescriptor> propertyDescriptors = new ArrayList<IPropertyDescriptor>();

	public UmlElement() {
		createPropertyDescriptors();
	}

	protected void createPropertyDescriptors() {
	}

	@Override
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
