/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
package com.xiaobo.uml.model;

import java.util.ArrayList;
import java.util.List;

public class Compartment extends NamedElement implements IUmlContainer {

	private static final long serialVersionUID = 1L;

	public static final String ATTRIBUTE_ID = "attribute_compartment";

	public static final String METHOD_ID = "method_compartment";

	public static final String CHILD_PROP = "child";

	private List<Member> children = new ArrayList<Member>();

	private String id;

	public Compartment(String id) {
		setName("    ");
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public List<Member> getChildren() {
		return children;
	}

	public void addChild(IUmlElement element) {
		if (!(element instanceof Member)) {
			throw new IllegalArgumentException();
		}
		children.add((Member) element);
		firePropertyChange(CHILD_PROP);
	}

	public void addChild(IUmlElement element, int index) {
		if (!(element instanceof Member)) {
			throw new IllegalArgumentException();
		}
		children.add(index, (Member) element);
		firePropertyChange(CHILD_PROP);
	}

	public void removeChild(IUmlElement element) {
		if (!(element instanceof Member)) {
			throw new IllegalArgumentException();
		}
		children.remove((Member) element);
		firePropertyChange(CHILD_PROP);
	}

	/**
	 * not useful method
	 */
	public void setName(String name, String prohibitFire) {
		super.name = name;
	}
}