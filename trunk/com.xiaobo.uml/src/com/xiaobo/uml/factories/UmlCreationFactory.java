package com.xiaobo.uml.factories;

import org.eclipse.gef.requests.CreationFactory;

import com.xiaobo.uml.model.Aggregation;
import com.xiaobo.uml.model.Association;
import com.xiaobo.uml.model.Attribute;
import com.xiaobo.uml.model.Compartment;
import com.xiaobo.uml.model.Inheritance;
import com.xiaobo.uml.model.Method;
import com.xiaobo.uml.model.Type;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlCreationFactory implements CreationFactory {

	private Class type;

	public UmlCreationFactory(Class type) {
		setType(type);
	}

	public Object getNewObject() {
		if (type == Type.class) {
			Type type = new Type();
			Compartment fields = new Compartment(Compartment.ATTRIBUTE_ID);
			fields.setDescription("attributes scope");
			type.addChild(fields);
			Compartment methods = new Compartment(Compartment.METHOD_ID);
			methods.setDescription("methods scope");
			type.addChild(methods);
			return type;
		} else if (type == Attribute.class) {
			return new Attribute();
		} else if (type == Method.class) {
			return new Method();
		} else if (type == Inheritance.class) {
			return new Inheritance();
		} else if (type == Aggregation.class) {
			return new Aggregation(true, "0..*");
		} else if (type == Association.class) {
			return new Association("", "", false, false);
		}
		return null;
	}

	public Object getObjectType() {
		return getType();
	}

	public Class getType() {
		return type;
	}

	public void setType(Class type) {
		this.type = type;
	}
}
