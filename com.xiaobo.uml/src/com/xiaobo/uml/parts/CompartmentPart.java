package com.xiaobo.uml.parts;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.IFigure;
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
		calculateName();
		super.refreshVisuals();
	}

	/**
	 * solve the bug 1.0-Jun 23
	 */
	public void calculateName() {
		Compartment model = (Compartment) getModel();
		StringBuffer name = new StringBuffer(model.getName());
		String parentName = ((Type) getParent().getModel()).getStereotype()
				+ ((Type) getParent().getModel()).getName();

		while (name.length() < parentName.length() * 3) {
			name.append(" ");
		}

		while (name.length() > parentName.length() * 3
				&& name.toString().endsWith(" ") && name.length() > 15) {
			name.deleteCharAt(name.length() - 1);
		}

		model.setName(name.toString(), "prohibit to fire the propertyChange");
	}
}
