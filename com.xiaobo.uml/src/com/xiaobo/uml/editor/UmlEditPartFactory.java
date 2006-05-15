package com.xiaobo.uml.editor;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.xiaobo.uml.model.Aggregation;
import com.xiaobo.uml.model.CompartmentModel;
import com.xiaobo.uml.model.Inheritance;
import com.xiaobo.uml.model.MemberModel;
import com.xiaobo.uml.model.TypeModel;
import com.xiaobo.uml.model.UmlModel;
import com.xiaobo.uml.parts.AggregationPart;
import com.xiaobo.uml.parts.CompartmentPart;
import com.xiaobo.uml.parts.InheritancePart;
import com.xiaobo.uml.parts.MemberPart;
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
		} else if (model instanceof TypeModel) {
			return new TypePart();
		} else if (model instanceof CompartmentModel) {
			return new CompartmentPart();
		} else if (model instanceof MemberModel) {
			return new MemberPart();
		} else if (model instanceof Inheritance) {
			return new InheritancePart();
		} else if (model instanceof Aggregation) {
			return new AggregationPart();
		}
		return null;
	}
}
