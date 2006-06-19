package com.xiaobo.uml.parts;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;

import com.xiaobo.uml.figure.CompartmentFigure;
import com.xiaobo.uml.model.IUmlContainer;
import com.xiaobo.uml.policies.CompartmentLayoutEditPolicy;

/**
 * 
 * @author xiaobo
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class CompartmentPart extends NamedElementPart {

	protected IFigure createFigure() {
		return new CompartmentFigure();
	}

	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.CONTAINER_ROLE,
				new CompartmentLayoutEditPolicy());
	}

	public void propertyChange(PropertyChangeEvent event) {
		refreshChildren();
		super.propertyChange(event);
	}

	protected List getModelChildren() {
		return ((IUmlContainer) getModel()).getChildren();
	}
}
