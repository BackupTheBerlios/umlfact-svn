package com.xiaobo.uml.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlModel extends UmlElement implements IUmlContainer {

	private static final String CHILD_PROP = "child";

	private List types = new ArrayList();

	public List getChildren() {
		return types;
	}

	public void addChild(IUmlElement element) {
		if (!(element instanceof TypeModel)) {
			throw new IllegalArgumentException();
		}
		types.add(element);
		firePropertyChange(CHILD_PROP);
	}

	public void addChild(IUmlElement element, int index) {
		if (!(element instanceof TypeModel)) {
			throw new IllegalArgumentException();
		}
		types.add(index, element);
		firePropertyChange(CHILD_PROP);
	}

	public void removeChild(IUmlElement element) {
		if (!(element instanceof TypeModel)) {
			throw new IllegalArgumentException();
		}
		types.remove(element);
		firePropertyChange(CHILD_PROP);
	}

}
