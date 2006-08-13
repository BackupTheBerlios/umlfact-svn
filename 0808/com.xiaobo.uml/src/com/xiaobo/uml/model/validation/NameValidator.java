package com.xiaobo.uml.model.validation;

import java.io.Serializable;
import java.util.Iterator;

import org.eclipse.jface.viewers.ICellEditorValidator;

import com.xiaobo.uml.model.INamedElement;
import com.xiaobo.uml.model.IUmlContainer;

/**
 * 
 * @author xiaobo. Created on Aug 13, 2006.
 * 
 * Copyright 2006 by Xiaobo Sun. All rights reserved.
 * 
 */
public class NameValidator implements ICellEditorValidator, Serializable {

	private static final long serialVersionUID = 1L;

	private INamedElement element;

	private IUmlContainer container;

	public NameValidator(INamedElement element, IUmlContainer container) {
		this.element = element;
		this.container = container;
	}

	public String isValid(Object value) {
		for (Iterator i = container.getChildren().iterator(); i.hasNext();) {
			INamedElement named = (INamedElement) i.next();
			if (element != named && value.equals(named.getName())) {
				return "This name is already used by other component.";
			}
		}
		return null;
	}
}
