package org.xiaobo.uml.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.xiaobo.uml.model.UmlElement;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlElementEditPart extends AbstractGraphicalEditPart implements
		PropertyChangeListener {

	public void activate() {
		if (getModel() != null && getModel() instanceof UmlElement) {
			((UmlElement) getModel()).addPropertyChangeListener(this);
		}
		super.activate();
	}

	public void deactivate() {
		if (getModel() != null && getModel() instanceof UmlElement) {
			((UmlElement) getModel()).removePropertyChangeListener(this);
		}
		super.deactivate();
	}

	protected IFigure createFigure() {
		// TODO Auto-generated method stub
		return null;
	}

	protected void createEditPolicies() {
		// TODO Auto-generated method stub

	}

	protected List getModelChildren() {
		if (getModel() instanceof UmlElement) {
			return ((UmlElement) getModel()).getChildren();
		}
		return super.getModelChildren();
	}

	public void propertyChange(PropertyChangeEvent evt) {
		String pName = evt.getPropertyName();
		if (pName.equals(UmlElement.PRO_FIGURE)) {
			this.refreshVisuals();
		}
		if (pName.equals(UmlElement.PRO_CHILD)) {
			this.refreshChildren();
			this.refreshVisuals();
		}
	}
}
