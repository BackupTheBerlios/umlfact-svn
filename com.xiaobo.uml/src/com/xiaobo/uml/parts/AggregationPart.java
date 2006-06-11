package com.xiaobo.uml.parts;

import java.beans.PropertyChangeEvent;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;

import com.xiaobo.uml.figure.AggregationFigure;
import com.xiaobo.uml.model.Aggregation;
import com.xiaobo.uml.policies.ConnectionSelectEditPolicy;
import com.xiaobo.uml.policies.UmlComponentEditPolicy;

/**
 * 
 * @author xiaobo
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class AggregationPart extends ConnectionPart {

	protected IFigure createFigure() {
		Aggregation aggr = (Aggregation) getModel();
		AggregationFigure conn = new AggregationFigure(aggr.isComposition(),
				aggr.getSourceString());
		// conn.setConnectionRouter(new ManhattanConnectionRouter());
		return conn;
	}

	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE,
				new UmlComponentEditPolicy());
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE,
				new ConnectionSelectEditPolicy());
	}

	protected void refreshVisuals() {
		super.refreshVisuals();
		Aggregation aggr = (Aggregation) getModel();
		AggregationFigure conn = (AggregationFigure) getFigure();
		conn.setComposition(aggr.isComposition());
		conn.setSourceString(aggr.getSourceString());
	}

	public void propertyChange(PropertyChangeEvent event) {
		refreshVisuals();
	}
}