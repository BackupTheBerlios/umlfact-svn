package com.xiaobo.uml.model;

import java.util.List;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class CompartmentModel extends UmlElement implements IUmlContainer {

	public static final String FIELD_ID = "field";

	public static final String CONSTRUCTOR_ID = "constructor";

	public static final String METHOD_ID = "method";

	private String id;

	public CompartmentModel() {

	}

	public CompartmentModel(String id) {
		this.id = id;
	}

	public List getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addChild(IUmlElement element) {
		// TODO Auto-generated method stub

	}

	public void addChild(IUmlElement element, int index) {
		// TODO Auto-generated method stub

	}

	public void removeChild(IUmlElement element) {
		// TODO Auto-generated method stub

	}

	public String getId() {
		return id;
	}

}
