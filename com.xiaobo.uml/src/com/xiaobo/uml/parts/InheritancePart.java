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

public class InheritancePart extends AbstractConnectionEditPart {

	protected IFigure createFigure() {
		PolylineConnection conn = new ConnectionFigure();

		PolylineDecoration decoration = new PolylineDecoration();
		PointList decorationPointList = new PointList();
		decorationPointList.addPoint(0, 0);
		decorationPointList.addPoint(0, 1);
		decorationPointList.addPoint(1, 0);
		decorationPointList.addPoint(0, -1);
		decorationPointList.addPoint(0, 0);
		decoration.setTemplate(decorationPointList);
		conn.setTargetDecoration(decoration);

		// FanRouter router = new FanRouter();
		// router.setSeparation(40);
		// conn.setConnectionRouter(router);

		return conn;
	}

	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE,
				new UmlComponentEditPolicy());
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE,
				new ConnectionSelectEditPolicy());
	}

}
