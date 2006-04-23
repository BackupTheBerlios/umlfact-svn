package com.xiaobo.uml.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;

import com.xiaobo.uml.figure.ConnectionFigure;
import com.xiaobo.uml.policies.ConnectionSelectEditPolicy;
import com.xiaobo.uml.policies.UmlComponentEditPolicy;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public abstract class ConnectionPart extends AbstractConnectionEditPart {

	protected IFigure createFigure() {
		PolylineConnection conn = new ConnectionFigure();
		conn.setTargetDecoration(new PolygonDecoration());
		return conn;
	}

	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE,
				new UmlComponentEditPolicy());
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE,
				new ConnectionSelectEditPolicy());
	}
}
