package com.xiaobo.uml.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ShortestPathConnectionRouter;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.LayerConstants;

import com.xiaobo.uml.figure.UmlModelFigure;
import com.xiaobo.uml.model.UmlModel;
import com.xiaobo.uml.policies.UmlModelXYLayoutEditPolicy;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlModelPart extends UmlElementPart implements
		PropertyChangeListener {

	protected IFigure createFigure() {
		Figure figure = new UmlModelFigure();
		ConnectionLayer connectionLayer = (ConnectionLayer) getLayer(LayerConstants.CONNECTION_LAYER);
		connectionLayer.setConnectionRouter(new ShortestPathConnectionRouter(
				figure));
		return figure;
	}

	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE,
				new UmlModelXYLayoutEditPolicy());
	}

	/**
	 * This is what causes children EditParts to be created.
	 */
	protected List getModelChildren() {
		return ((UmlModel) getModel()).getChildren();
	}

	public void propertyChange(PropertyChangeEvent evt) {
		refreshChildren();
	}
}
