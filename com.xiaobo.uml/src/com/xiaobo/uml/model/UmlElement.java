package com.xiaobo.uml.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlElement extends UmlPropertySource implements IUmlElement {
	/**
	 * for the property sheet.
	 */
	private transient List propertyDescriptors = new ArrayList();

	public UmlElement() {
		createPropertyDescriptors();
	}

	protected void createPropertyDescriptors() {
	}

	public IPropertyDescriptor[] getPropertyDescriptors() {
		IPropertyDescriptor[] pD = new IPropertyDescriptor[propertyDescriptors
				.size()];
		for (int i = 0; i < propertyDescriptors.size(); i++) {
			pD[i] = (IPropertyDescriptor) propertyDescriptors.get(i);
		}
		return pD;
	}

	protected void addPropertyDescriptor(IPropertyDescriptor descriptor) {
		propertyDescriptors.add(descriptor);
	}
}
