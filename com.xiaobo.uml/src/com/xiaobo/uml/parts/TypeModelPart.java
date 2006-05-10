package com.xiaobo.uml.parts;

import java.beans.PropertyChangeEvent;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.CreateConnectionRequest;

import com.xiaobo.uml.connect.LeftRightAnchor;
import com.xiaobo.uml.connect.TopBottomAnchor;
import com.xiaobo.uml.figure.TypeModelFigure;
import com.xiaobo.uml.model.CompartmentModel;
import com.xiaobo.uml.model.IUmlContainer;
import com.xiaobo.uml.model.TypeModel;
import com.xiaobo.uml.model.UmlModel;
import com.xiaobo.uml.policies.TypeLayoutEditPolicy;
import com.xiaobo.uml.policies.TypeNodeEditPolicy;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */

public class TypeModelPart extends PositionableElementPart implements
		NodeEditPart {

	public void activate() {
		if (!isActive()) {
			super.activate();
			((UmlModel) getParent().getModel()).addPropertyChangeListener(this);
			for (Iterator i = getChildren().iterator(); i.hasNext();) {
				CompartmentPart child = (CompartmentPart) i.next();
				((CompartmentModel) child.getModel())
						.addPropertyChangeListener(this);
			}
		}
	}

	public void deactivate() {
		if (isActive()) {
			for (Iterator i = getChildren().iterator(); i.hasNext();) {
				CompartmentPart child = (CompartmentPart) i.next();
				((CompartmentModel) child.getModel())
						.removePropertyChangeListener(this);
			}
			((UmlModel) getParent().getModel())
					.removePropertyChangeListener(this);
			super.deactivate();
		}
	}

	protected IFigure createFigure() {
		return new TypeModelFigure();
	}

	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.CONNECTION_ROLE, new TypeNodeEditPolicy());
		installEditPolicy(EditPolicy.CONTAINER_ROLE, new TypeLayoutEditPolicy());
	}

	/**
	 * prefer at first refresh...() then super.propertyChange(event)
	 */
	public void propertyChange(PropertyChangeEvent event) {
		refreshSourceConnections();
		refreshTargetConnections();
		refreshChildren();
		super.propertyChange(event);
	}

	/**
	 * caused the children editparts to be created or updated.
	 */
	protected List getModelChildren() {
		return ((IUmlContainer) getModel()).getChildren();
	}

	/**
	 * caused the out-connections editparts to be created or updated.
	 */
	public List getModelSourceConnections() {
		TypeModel type = (TypeModel) getModel();
		return type.getOuts();
	}

	/**
	 * caused the out-connections editparts to be created or updated.
	 */
	public List getModelTargetConnections() {
		TypeModel type = (TypeModel) getModel();
		return type.getIns();
	}

	public ConnectionAnchor getSourceConnectionAnchor(
			ConnectionEditPart connection) {
		return new ChopboxAnchor(getFigure());
	}

	public ConnectionAnchor getTargetConnectionAnchor(
			ConnectionEditPart connection) {
		if (connection instanceof InheritancePart) {
			return new TopBottomAnchor(getFigure());
		} else {
			return new LeftRightAnchor(getFigure());
		}
	}

	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return new ChopboxAnchor(getFigure());
	}

	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		if (((CreateConnectionRequest) request).getNewObject() instanceof InheritancePart) {
			return new TopBottomAnchor(getFigure());
		} else {
			return new LeftRightAnchor(getFigure());
		}
	}
}
