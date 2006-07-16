package com.xiaobo.uml.model;

import org.eclipse.jface.viewers.ICellEditorValidator;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public interface INamedElement extends IUmlElement {

	public String getName();

	public void setName(String name);

	public void setNameValidator(ICellEditorValidator validator);
}
