package com.xiaobo.uml.factories;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.xiaobo.uml.model.Aggregation;
import com.xiaobo.uml.model.Association;
import com.xiaobo.uml.model.Attribute;
import com.xiaobo.uml.model.Compartment;
import com.xiaobo.uml.model.Inheritance;
import com.xiaobo.uml.model.Method;
import com.xiaobo.uml.model.Type;
import com.xiaobo.uml.model.UmlModel;
import com.xiaobo.uml.parts.AggregationPart;
import com.xiaobo.uml.parts.AssociationPart;
import com.xiaobo.uml.parts.AttributePart;
import com.xiaobo.uml.parts.CompartmentPart;
import com.xiaobo.uml.parts.InheritancePart;
import com.xiaobo.uml.parts.MethodPart;
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
		} else if (model instanceof Type) {
			return new TypePart();
		} else if (model instanceof Compartment) {
			return new CompartmentPart();
		} else if (model instanceof Attribute) {
			return new AttributePart();
		} else if (model instanceof Method) {
			return new MethodPart();
		} else if (model instanceof Inheritance) {
			return new InheritancePart();
		} else if (model instanceof Aggregation) {
			return new AggregationPart();
		} else if (model instanceof Association) {
			return new AssociationPart();
		}
		return null;
	}
}
