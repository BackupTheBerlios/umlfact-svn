package com.xiaobo.uml.figure;

import org.eclipse.draw2d.ConnectionEndpointLocator;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.geometry.PointList;

/**
 * 
 * @author xiaobo. Created on Jun 18, 2006.
 * 
 * Copyright 2006 by Xiaobo Sun. All rights reserved.
 * 
 */
public class AssociationFigure extends ConnectionFigure {

	private Label sourceLabel;

	private Label targetLabel;

	public AssociationFigure(String sourceString, String targetString,
			boolean sourceNavigation, boolean targetNavigation) {
		ConnectionEndpointLocator sourceLocator = new ConnectionEndpointLocator(
				this, false);
		sourceLocator.setUDistance(10);
		sourceLocator.setVDistance(10);
		sourceLabel = new Label(sourceString);
		add(sourceLabel, sourceLocator);
		ConnectionEndpointLocator targetLocator = new ConnectionEndpointLocator(
				this, true);
		targetLocator.setUDistance(10);
		sourceLocator.setVDistance(10);
		targetLabel = new Label(targetString);
		add(targetLabel, targetLocator);

		setSourceNavigation(sourceNavigation);
		setTargetNavigation(targetNavigation);
	}

	public void setSourceString(String sourceString) {
		sourceLabel.setText(sourceString);
	}

	public void setTargetString(String targetString) {
		targetLabel.setText(targetString);
	}

	public void setSourceNavigation(boolean sourceNavigation) {
		if (sourceNavigation) {
			PolylineDecoration decoration = new PolylineDecoration();
			decoration.setTemplate(getSourceNavigationPointList());
			setSourceDecoration(decoration);
		} else {
			PolylineDecoration decoration = new PolylineDecoration();
			decoration.setTemplate(getNullNavigationPointList());
			setTargetDecoration(decoration);
		}
	}

	public void setTargetNavigation(boolean targetNavigation) {
		if (targetNavigation) {
			PolylineDecoration decoration = new PolylineDecoration();
			decoration.setTemplate(getTargetNavigationPointList());
			setTargetDecoration(decoration);
		} else {
			PolylineDecoration decoration = new PolylineDecoration();
			decoration.setTemplate(getNullNavigationPointList());
			setTargetDecoration(decoration);
		}
	}

	public PointList getSourceNavigationPointList() {
		PointList decorationPointList = new PointList();
		decorationPointList.addPoint(0, 0);
		decorationPointList.addPoint(-1, 1);
		decorationPointList.addPoint(0, 0);
		decorationPointList.addPoint(-1, -1);
		return decorationPointList;
	}

	public PointList getTargetNavigationPointList() {
		PointList decorationPointList = new PointList();
		decorationPointList.addPoint(0, 0);
		decorationPointList.addPoint(-1, 1);
		decorationPointList.addPoint(0, 0);
		decorationPointList.addPoint(-1, -1);
		return decorationPointList;
	}

	public PointList getNullNavigationPointList() {
		PointList decorationPointList = new PointList();
		decorationPointList.addPoint(0, 0);
		return decorationPointList;
	}

}
