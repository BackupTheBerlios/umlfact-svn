package com.xiaobo.uml.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;

import com.xiaobo.uml.figure.ConnectionFigure;
import com.xiaobo.uml.policies.ConnectionSelectEditPolicy;
import com.xiaobo.uml.policies.UmlComponentEditPolicy;

/**
 * 
 * @author xiaobo
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class AggregationPart extends AbstractConnectionEditPart {

	protected IFigure createFigure() {
		PolylineConnection conn = new ConnectionFigure();

		PolylineDecoration decoration = new PolylineDecoration();
		PointList decorationPointList = new PointList();
		decorationPointList.addPoint(0, 0);
		decorationPointList.addPoint(1, 1);
		decorationPointList.addPoint(2, 0);
		decorationPointList.addPoint(1, -1);
		decorationPointList.addPoint(0, 0);
		decoration.setTemplate(decorationPointList);
		conn.setTargetDecoration(decoration);

		return conn;
	}

	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE,
				new UmlComponentEditPolicy());
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE,
				new ConnectionSelectEditPolicy());
	}

}
