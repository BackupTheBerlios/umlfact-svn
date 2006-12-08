package com.xiaobo.uml.parts;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.IFigure;

import com.xiaobo.uml.figure.AggregationFigure;
import com.xiaobo.uml.model.Aggregation;

/**
 * 
 * @author xiaobo
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class AggregationPart extends ConnectionPart {

	@Override
	protected IFigure createFigure() {
		Aggregation aggr = (Aggregation) getModel();
		AggregationFigure conn = new AggregationFigure(aggr.isComposition(),
				aggr.getSourceString());
		conn.setConnectionRouter(new BendpointConnectionRouter());
		return conn;
	}

	@Override
	protected void refreshVisuals() {
		super.refreshVisuals();
		Aggregation aggr = (Aggregation) getModel();
		AggregationFigure conn = (AggregationFigure) getFigure();
		conn.setComposition(aggr.isComposition());
		conn.setSourceString(aggr.getSourceString());
	}
}