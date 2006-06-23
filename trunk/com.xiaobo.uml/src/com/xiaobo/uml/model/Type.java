package com.xiaobo.uml.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ICellEditorValidator;

import com.xiaobo.uml.model.propertyDescriptor.UmlTextPropertyDescriptor;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class Type extends PositionableElement implements IUmlConnectionNode,
		IUmlContainer {

	private static final long serialVersionUID = 1L;

	private static final String CONNS_OUT_PROP = "Out";

	private static final String CONNS_IN_PROP = "In";

	private static final String CHILD_PROP = "child";

	private static final String STEREOTYPE_PROP = "stereotype";

	public static final String COLLAPSED_PROP = "collapsed";

	private List outs = new ArrayList();

	private List ins = new ArrayList();

	private List children = new ArrayList();

	private UmlTextPropertyDescriptor stereoTypePropertyDescriptor;

	private String stereotype;

	private boolean collapsed;

	public boolean isCollapsed() {
		return collapsed;
	}

	public void setCollapsed(boolean collapsed) {
		this.collapsed = collapsed;
		firePropertyChange(COLLAPSED_PROP);
	}

	public Type() {
		setName("Type");
		setStereotype("");
	}

	protected void createPropertyDescriptors() {
		super.createPropertyDescriptors();
		stereoTypePropertyDescriptor = new UmlTextPropertyDescriptor(
				STEREOTYPE_PROP, "Stereo type");
		addPropertyDescriptor(stereoTypePropertyDescriptor);
	}

	public void addOut(IUmlConnection out) {
		outs.add(out);
		firePropertyChange(CONNS_OUT_PROP);
	}

	public void addIn(IUmlConnection in) {
		ins.add(in);
		firePropertyChange(CONNS_IN_PROP);
	}

	public List getOuts() {
		return outs;
	}

	public List getIns() {
		return ins;
	}

	public void removeIn(IUmlConnection con) {
		ins.remove(con);
		firePropertyChange(CONNS_IN_PROP);
	}

	public void removeOut(IUmlConnection con) {
		outs.remove(con);
		firePropertyChange(CONNS_OUT_PROP);
	}

	public List getChildren() {
		return children;
	}

	public void addChild(IUmlElement element) {
		if (!(element instanceof Compartment)) {
			throw new IllegalArgumentException();
		}
		children.add(element);
		firePropertyChange(CHILD_PROP);
	}

	public void addChild(IUmlElement element, int index) {
		if (!(element instanceof Compartment)) {
			throw new IllegalArgumentException();
		}
		children.add(index, element);
		firePropertyChange(CHILD_PROP);
	}

	public void removeChild(IUmlElement element) {
		if (!(element instanceof Compartment)) {
			throw new IllegalArgumentException();
		}
		children.remove(element);
		firePropertyChange(CHILD_PROP);
	}

	public String getStereotype() {
		return stereotype;
	}

	public void setStereotype(String stereotype) {
		this.stereotype = stereotype;
		firePropertyChange(STEREOTYPE_PROP);
	}

	public Object getPropertyValue(Object id) {
		if (STEREOTYPE_PROP.equals((id))) {
			return getStereotype();
		}
		return super.getPropertyValue(id);
	}

	public boolean isPropertySet(Object id) {
		if (id.equals(STEREOTYPE_PROP)) {
			return true;
		}
		return super.isPropertySet(id);
	}

	public void resetPropertyValue(Object id) {
		super.resetPropertyValue(id);
	}

	public void setPropertyValue(Object id, Object value) {
		super.setPropertyValue(id, value);
		if (STEREOTYPE_PROP.equals((id))) {
			setStereotype((String) value);
		}
	}

	public void setNameValidator(ICellEditorValidator validator) {
		stereoTypePropertyDescriptor.setValidator(validator);
	}
}