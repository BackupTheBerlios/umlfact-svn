package org.xiaobo.uml.editor.palette;

import org.eclipse.gef.requests.CreationFactory;
import org.xiaobo.uml.model.UmlMember;
import org.xiaobo.uml.model.UmlType;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright ? 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlCreationFactory implements CreationFactory {

	private Class type;

	public UmlCreationFactory(Class type) {
		setType(type);
	}

	public Object getNewObject() {
		if (type == UmlType.class) {
			return new UmlType();
		}
		if (type == UmlMember.class)
			return new UmlMember();
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
