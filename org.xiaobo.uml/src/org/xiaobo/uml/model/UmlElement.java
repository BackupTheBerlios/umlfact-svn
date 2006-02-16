package org.xiaobo.uml.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlElement extends UlmPropertySource implements IUmlElement {

	public static final String PRO_FIGURE = "__figure__property";

	public static final String PRO_CHILD = "__child__property";

	public static final String PRO_CONNECTION_IN = "__connection__in";

	public static final String PRO_CONNECTION_OUT = "__connection__out";

	protected PropertyChangeSupport support = new PropertyChangeSupport(this);

	private transient List propertyDescriptors = new ArrayList();

	protected UmlElement parent;

	protected List children;

	public UmlElement() {
		createPropertyDescriptors();
	}

	public UmlElement getParent() {
		return parent;
	}

	public void setParent(UmlElement parent) {
		this.parent = parent;
	}

	public List getChildren() {
		if (children == null)
			children = new ArrayList();
		return children;
	}

	public void setChildren(List children) {
		this.children = children;
	}

	public void addChild(UmlElement child) {
		addChild(-1, child);
	}

	public void addChild(int index, UmlElement child) {
		if (index == -1) {
			getChildren().add(child);
		} else {
			getChildren().add(index, child);
		}
		child.setParent(this);
		this.fireChildenChange(child);
	}

	public void removeChild(UmlElement child) {
		child.setParent(null);
		getChildren().remove(child);
		this.fireChildenChange(child);
	}

	public void addPropertyChangeListener(PropertyChangeListener l) {
		support.addPropertyChangeListener(l);
	}

	public void removePropertyChangeListener(PropertyChangeListener l) {
		support.removePropertyChangeListener(l);
	}

	public void fireFigurePropertyChange(Object old, Object now) {
		support.firePropertyChange(PRO_FIGURE, old, now);
	}

	public void fireChildenChange(UmlElement child) {
		support.firePropertyChange(PRO_CHILD, null, child);
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
