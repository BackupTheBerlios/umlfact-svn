package com.xiaobo.uml.figure;

import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.geometry.PointList;

/**
 * 
 * @author Xiaobo Sun. Created on 16.06.2006.
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class InheritanceFigure extends ConnectionFigure {

	public InheritanceFigure() {
		PolylineDecoration decoration = new PolylineDecoration();
		decoration.setTemplate(getPointList());
		setTargetDecoration(decoration);
	}

	public PointList getPointList() {
		PointList decorationPointList = new PointList();
		decorationPointList.addPoint(0, 0);
		decorationPointList.addPoint(0, 1);
		decorationPointList.addPoint(1, 0);
		decorationPointList.addPoint(0, -1);
		decorationPointList.addPoint(0, 0);
		return decorationPointList;
	}
}
