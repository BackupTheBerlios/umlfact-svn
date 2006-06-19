package com.xiaobo.uml.figure;

import org.eclipse.draw2d.ConnectionEndpointLocator;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.geometry.PointList;

/**
 * 
 * @author xiaobo
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class AggregationFigure extends ConnectionFigure {

	private Label sourceLabel;

	public AggregationFigure(boolean isComposition, String sourceString) {
		setComposition(isComposition);

		ConnectionEndpointLocator locator = new ConnectionEndpointLocator(this,
				false);
		locator.setUDistance(10);
		locator.setVDistance(10);
		sourceLabel = new Label(sourceString);
		add(sourceLabel, locator);
	}

	public PointList getPointList() {
		PointList decorationPointList = new PointList();
		decorationPointList.addPoint(0, 0);
		decorationPointList.addPoint(-1, 1);
		decorationPointList.addPoint(-2, 0);
		decorationPointList.addPoint(-1, -1);
		decorationPointList.addPoint(0, 0);
		return decorationPointList;
	}

	public void setComposition(boolean isComposition) {
		if (isComposition) {
			PolygonDecoration decoration = new PolygonDecoration();
			decoration.setTemplate(getPointList());
			setTargetDecoration(decoration);
		} else {
			PolylineDecoration decoration = new PolylineDecoration();
			decoration.setTemplate(getPointList());
			setTargetDecoration(decoration);
		}
	}

	public void setSourceString(String sourceString) {
		sourceLabel.setText(sourceString);
	}
}
