package com.xiaobo.uml.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.FanRouter;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ShortestPathConnectionRouter;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
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

	@Override
	protected IFigure createFigure() {
		/**
		 * if use ToolbarLayout, the underlying problems is solved. 1) change
		 * the length of the member'name caused the change of type size without
		 * any refresh of type figure. 2) when initiate the editor, the type is
		 * expand (inside compartments is showed).
		 */
		// Figure figure = new Figure();
		// ToolbarLayout layout = new ToolbarLayout(false);
		// figure.setLayoutManager(layout);
		/**
		 * but normally we gotta use XYLayout and ToolbarLayout together.
		 */
		Figure figure = new UmlModelFigure();

		ConnectionLayer connectionLayer = (ConnectionLayer) getLayer(LayerConstants.CONNECTION_LAYER);
		FanRouter router = new FanRouter();
		router.setSeparation(20);
		ShortestPathConnectionRouter spRouter = new ShortestPathConnectionRouter(
				figure);
		router.setNextRouter(spRouter);
		connectionLayer.setConnectionRouter(router);

		return figure;
	}

	@Override
	protected void createEditPolicies() {
		/**
		 * ToolbarLayout
		 */
		// installEditPolicy(EditPolicy.LAYOUT_ROLE,
		// new UmlModelToolbarLayoutEditPolicy());
		/**
		 * XYLayoutPolicy
		 */
		installEditPolicy(EditPolicy.LAYOUT_ROLE,
				new UmlModelXYLayoutEditPolicy());
	}

	/**
	 * This is what causes children EditParts to be created.
	 */
	@Override
	protected List getModelChildren() {
		return ((UmlModel) getModel()).getChildren();
	}

	public void propertyChange(PropertyChangeEvent evt) {
		refreshChildren();
	}
	/**
	 * This causes the child part refreshVisuals() and refreshChildren().
	 */
	@Override
	protected void refreshChildren() {
		super.refreshChildren();
		for (Iterator i = getChildren().iterator(); i.hasNext();) {
			GraphicalEditPart child = (GraphicalEditPart) i.next();
			child.refresh();
		}
	}
}
