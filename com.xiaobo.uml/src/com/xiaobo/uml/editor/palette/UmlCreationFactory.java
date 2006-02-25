package com.xiaobo.uml.editor.palette;

import org.eclipse.gef.requests.CreationFactory;

import com.xiaobo.uml.model.ConnectionModel;
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
			return new TypeModel();
		}
		if (type == ConnectionModel.class) {
			ConnectionModel connModel = new ConnectionModel();
			System.out.println("creation connection: " + connModel);
			return connModel;
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
