package com.xiaobo.uml.factories;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.xiaobo.uml.parts.UmlElementTreePart;

/**
 * 
 * @author Xiaobo Sun. Created on 19.06.2006.
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class UmlTreePartFactory implements EditPartFactory {

	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = new UmlElementTreePart();
		part.setModel(model);
		return part;
	}

}
