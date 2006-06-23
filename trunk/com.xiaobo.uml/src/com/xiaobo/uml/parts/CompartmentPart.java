package com.xiaobo.uml.parts;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.gef.EditPolicy;

import com.xiaobo.uml.figure.CompartmentFigure;
import com.xiaobo.uml.model.Compartment;
import com.xiaobo.uml.model.IUmlContainer;
import com.xiaobo.uml.model.Type;
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
		super.propertyChange(event);
		refreshChildren();
	}

	protected List getModelChildren() {
		return ((IUmlContainer) getModel()).getChildren();
	}

	public void refreshVisuals() {
		CompartmentFigure figure = (CompartmentFigure) getFigure();
		Label figureLabel = figure.getLabel();
		Compartment compartment = (Compartment) getModel();
		/**
		 * solve the bug 1.0-Jun 23
		 */
		StringBuffer compartmentName = new StringBuffer(compartment.getName());
		String typeName = ((Type) getParent().getModel()).getName();
		while (compartmentName.length() < typeName.length() + 8) {
			compartmentName.append(" ");
		}
		compartment.setName(compartmentName.toString(),
				"prohibit to fire the propertyChange");

		figureLabel.setText(compartment.getName());
	}
}
