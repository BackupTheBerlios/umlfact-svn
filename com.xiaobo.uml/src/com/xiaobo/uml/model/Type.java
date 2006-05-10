package com.xiaobo.uml.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author xiaobo
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class Type extends PositionableElement implements IUmlConnectionNode {

	private static final String CONNS_OUT_PROP = "Out";

	private static final String CONNS_IN_PROP = "In";

	private static final String ATTRIBUTS_PROP = "attributes";

	private static final String OPERATIONS_PROP = "operations";

	private List outs = new ArrayList();

	private List ins = new ArrayList();

	private List attributes = new ArrayList();

	private List operations = new ArrayList();

	public Type() {
		setName("newType");
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

	public List getAttributes() {
		return attributes;
	}

	public void addAttribute(IUmlElement element) {
		if (!(element instanceof MemberModel)) {
			throw new IllegalArgumentException();
		}
		attributes.add(element);
		firePropertyChange(ATTRIBUTS_PROP);
	}

	public void addAttribute(IUmlElement element, int index) {
		if (!(element instanceof MemberModel)) {
			throw new IllegalArgumentException();
		}
		attributes.add(index, element);
		firePropertyChange(ATTRIBUTS_PROP);
	}

	public void removeAttribute(IUmlElement element) {
		if (!(element instanceof MemberModel)) {
			throw new IllegalArgumentException();
		}
		attributes.remove(element);
		firePropertyChange(ATTRIBUTS_PROP);
	}

	public List getOperations() {
		return operations;
	}

	public void addOperation(IUmlElement element) {
		if (!(element instanceof MemberModel)) {
			throw new IllegalArgumentException();
		}
		operations.add(element);
		firePropertyChange(OPERATIONS_PROP);
	}

	public void addOperation(IUmlElement element, int index) {
		if (!(element instanceof MemberModel)) {
			throw new IllegalArgumentException();
		}
		operations.add(index, element);
		firePropertyChange(OPERATIONS_PROP);
	}

	public void removeOperation(IUmlElement element) {
		if (!(element instanceof MemberModel)) {
			throw new IllegalArgumentException();
		}
		operations.remove(element);
		firePropertyChange(OPERATIONS_PROP);
	}
}
