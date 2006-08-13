package com.xiaobo.uml.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.xiaobo.uml.model.propertyDescriptor.UmlComboBoxPropertyDescriptor;

/**
 * 
 * @author xiaobo
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class Inheritance extends ConnectionModel {

	private static final long serialVersionUID = 1L;

	private static final String ISIMPL_PROP = "isImplement";

	private UmlComboBoxPropertyDescriptor isImplPropertyDecriptor;

	private boolean isImpl;

	public Inheritance(boolean impl) {
		setImpl(impl);
	}

	public Inheritance(Element element) {
		boolean tmp;
		if (element.getAttribute("isImpl").equals("true")) {
			tmp = true;
		} else {
			tmp = false;
		}
		setImpl(tmp);
	}

	protected void createPropertyDescriptors() {
		super.createPropertyDescriptors();
		isImplPropertyDecriptor = new UmlComboBoxPropertyDescriptor(
				ISIMPL_PROP, "isImplements", new String[] { "true", "false" });
		addPropertyDescriptor(isImplPropertyDecriptor);
	}

	public boolean isImpl() {
		return isImpl;
	}

	public void setImpl(boolean impl) {
		this.isImpl = impl;
		firePropertyChange(ISIMPL_PROP);
	}

	public Object getPropertyValue(Object id) {
		if (ISIMPL_PROP.equals(id)) {
			if (isImpl()) {
				return new Integer(0);
			}
			return new Integer(1);
		}
		return super.getPropertyValue(id);
	}

	public boolean isPropertySet(Object id) {
		if (id.equals(ISIMPL_PROP)) {
			return true;
		}
		return super.isPropertySet(id);
	}

	public void resetPropertyValue(Object id) {
		super.resetPropertyValue(id);
	}

	public void setPropertyValue(Object id, Object value) {
		super.setPropertyValue(id, value);
		if (ISIMPL_PROP.equals(id)) {
			if (value.equals(new Integer(0))) {
				setImpl(true);
			} else {
				setImpl(false);
			}
		}
	}

	public Element toDom(Document doc) {
		System.out.println("inheritance toxml");

		Element subRoot = doc.createElement("inheritance");
		subRoot.setAttribute("uid", serialVersionUID + "");
		subRoot.setAttribute("isImpl", isImpl + "");
		subRoot.setAttribute("sourceTypeName", ((Type) getSource()).getName());
		subRoot.setAttribute("targetTypeName", ((Type) getTarget()).getName());

		return subRoot;
	}
}
