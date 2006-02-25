package com.xiaobo.uml.editor;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.xiaobo.uml.model.ConnectionModel;
import com.xiaobo.uml.model.TypeModel;
import com.xiaobo.uml.model.UmlModel;
import com.xiaobo.uml.parts.ConnectionPart;
import com.xiaobo.uml.parts.TypePart;
import com.xiaobo.uml.parts.UmlModelPart;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlEditPartFactory implements EditPartFactory {
	
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = getPartForElement(model);
		part.setModel(model);
		return part;
	}

	private EditPart getPartForElement(Object model) {
		if (model instanceof UmlModel) {
			return new UmlModelPart();
		}
		if (model instanceof TypeModel) {
			return new TypePart();
		}
		if (model instanceof ConnectionModel) {
            return new ConnectionPart();
        }
		return null;
	}
}
