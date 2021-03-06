package com.xiaobo.uml.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class Compartment extends NamedElement implements IUmlContainer {

	private static final long serialVersionUID = 1L;

	public static final String ATTRIBUTE_ID = "attribute_compartment";

	public static final String METHOD_ID = "method_compartment";

	public static final String CHILD_PROP = "child";

	private List children = new ArrayList();

	private String id;

	public Compartment(String id) {
		setName("");
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public List getChildren() {
		return children;
	}

	public void addChild(IUmlElement element) {
		if (!(element instanceof Member)) {
			throw new IllegalArgumentException();
		}
		children.add(element);
		firePropertyChange(CHILD_PROP);
	}

	public void addChild(IUmlElement element, int index) {
		if (!(element instanceof Member)) {
			throw new IllegalArgumentException();
		}
		children.add(index, element);
		firePropertyChange(CHILD_PROP);
	}

	public void removeChild(IUmlElement element) {
		if (!(element instanceof Member)) {
			throw new IllegalArgumentException();
		}
		children.remove(element);
		firePropertyChange(CHILD_PROP);
	}

	public void setName(String name, String prohibitFire) {
		super.name = name;
	}
}
