package com.xiaobo.uml.editor.palette;

import org.eclipse.gef.requests.CreationFactory;

import com.xiaobo.uml.model.Aggregation;
import com.xiaobo.uml.model.CompartmentModel;
import com.xiaobo.uml.model.Inheritance;
import com.xiaobo.uml.model.MemberModel;
import com.xiaobo.uml.model.TypeModel;

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
		if (type == TypeModel.class) {
			TypeModel type = new TypeModel();
			CompartmentModel fields = new CompartmentModel();
			fields.setName("                  ");
			type.addChild(fields);
			CompartmentModel methods = new CompartmentModel();
			methods.setName("                 ");
			type.addChild(methods);
			return type;
		} else if (type == Inheritance.class) {
			return new Inheritance();
		} else if (type == Aggregation.class) {
			return new Aggregation();
		} else if (type == MemberModel.class) {
			return new MemberModel();
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
