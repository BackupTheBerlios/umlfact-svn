package com.xiaobo.uml.parts;

import java.beans.PropertyChangeEvent;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;

import com.xiaobo.uml.IIconConstants;
import com.xiaobo.uml.UmlPlugin;
import com.xiaobo.uml.figure.ILabeledFigure;
import com.xiaobo.uml.figure.TypeFigure;
import com.xiaobo.uml.model.Compartment;
import com.xiaobo.uml.model.IUmlContainer;
import com.xiaobo.uml.model.Type;
import com.xiaobo.uml.model.UmlModel;
import com.xiaobo.uml.policies.ConnectionNodeEditPolicy;
import com.xiaobo.uml.policies.TypeLayoutEditPolicy;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */

public class TypePart extends PositionableElementPart implements NodeEditPart {

	public void activate() {
		if (!isActive()) {
			super.activate();
			/**
			 * cause to refreshVisuals of type when properties of parent
			 * umlmodel are changed.
			 */
			((UmlModel) getParent().getModel()).addPropertyChangeListener(this);
			/**
			 * This works, only because the typeModel contains the compartments
			 * before the typepart is created.
			 * 
			 * cause to refreshVisuals of type when properies of child
			 * compartment are changed.
			 */
			for (Iterator i = getChildren().iterator(); i.hasNext();) {
				CompartmentPart compartmentPart = (CompartmentPart) i.next();
				((Compartment) compartmentPart.getModel())
						.addPropertyChangeListener(this);
			}
		}
	}

	public void deactivate() {
		if (isActive()) {
			for (Iterator i = getChildren().iterator(); i.hasNext();) {
				CompartmentPart compartmentPart = (CompartmentPart) i.next();
				((Compartment) compartmentPart.getModel())
						.removePropertyChangeListener(this);
			}
			((UmlModel) getParent().getModel())
					.removePropertyChangeListener(this);
			super.deactivate();
		}
	}

	protected IFigure createFigure() {
		return new TypeFigure();
	}

	public void performRequest(Request req) {
		super.performRequest(req);
		if (req.getType() == RequestConstants.REQ_OPEN) {
			getType().setCollapsed(!getType().isCollapsed());
		}
	}

	public Type getType() {
		return (Type) getModel();
	}

	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.CONNECTION_ROLE,
				new ConnectionNodeEditPolicy());
		installEditPolicy(EditPolicy.CONTAINER_ROLE, new TypeLayoutEditPolicy());
	}

	/**
	 * prefer at first refresh...() then super.propertyChange(event)
	 */
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getPropertyName() == Type.COLLAPSED_PROP) {
			TypeFigure figure = (TypeFigure) getFigure();
			if (getType().isCollapsed()) {
				figure.remove(getContentPane());
			} else {
				figure.add(getContentPane());
			}
		}
		refreshSourceConnections();
		refreshTargetConnections();
		refreshChildren();
		super.propertyChange(event);
	}

	protected void refreshChildren() {
		super.refreshChildren();
		for (Iterator i = getChildren().iterator(); i.hasNext();) {
			GraphicalEditPart child = (GraphicalEditPart) i.next();
			child.refresh();
		}
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
		Type type = (Type) getModel();
		return type.getOuts();
	}

	/**
	 * caused the out-connections editparts to be created or updated.
	 */
	public List getModelTargetConnections() {
		Type type = (Type) getModel();
		return type.getIns();
	}

	protected void refreshVisuals() {
		super.refreshVisuals();
		ILabeledFigure figure = (ILabeledFigure) getFigure();
		Label label = figure.getLabel();

		if (getType().getStereotype().equals("")) {
			label.setText(getType().getName());
		} else {
			label.setText("<<" + getType().getStereotype() + ">>  "
					+ getType().getName());
		}

		if (getType().isCollapsed()) {
			label.setIcon(UmlPlugin.getImage(IIconConstants.FOLDER_ICON));
		} else {
			label.setIcon(UmlPlugin.getImage(IIconConstants.TYPE_ICON));
		}
	}

	public ConnectionAnchor getSourceConnectionAnchor(
			ConnectionEditPart connection) {
		return new ChopboxAnchor(getFigure());
	}

	public ConnectionAnchor getTargetConnectionAnchor(
			ConnectionEditPart connection) {
		return new ChopboxAnchor(getFigure());
	}

	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return new ChopboxAnchor(getFigure());
	}

	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return new ChopboxAnchor(getFigure());
	}
}
