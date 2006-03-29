package com.xiaobo.uml.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;

import com.xiaobo.uml.layout.GraphLayout;
import com.xiaobo.uml.model.UmlModel;
import com.xiaobo.uml.policies.GraphEditPolicy;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlModelPart extends UmlElementPart implements
		PropertyChangeListener {

	protected IFigure createFigure() {
		// Figure figure = new UmlModelFigure();
		// ConnectionLayer connectionLayer = (ConnectionLayer)
		// getLayer(LayerConstants.CONNECTION_LAYER);
		// connectionLayer.setConnectionRouter(new ShortestPathConnectionRouter(
		// figure));
		Figure figure = new Figure();
		figure.setLayoutManager(new GraphLayout(this));
		return figure;
	}

	protected void createEditPolicies() {
		// installEditPolicy(EditPolicy.LAYOUT_ROLE,
		// new UmlModelXYLayoutEditPolicy());
		installEditPolicy(EditPolicy.CONTAINER_ROLE, new GraphEditPolicy());
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

	// protected void refreshChildren() {
	// super.refreshChildren();
	// for (Iterator i = getChildren().iterator(); i.hasNext();) {
	// GraphicalEditPart child = (GraphicalEditPart) i.next();
	// child.refresh();
	// }
	// }
}
