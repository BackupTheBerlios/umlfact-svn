package com.xiaobo.uml.policies;

import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class ConnectionSelectEditPolicy extends ConnectionEndpointEditPolicy {

	private PolylineConnection getConnectionFigure() {
		return (PolylineConnection) getHostFigure();
	}

	@Override
	protected void addSelectionHandles() {
		super.addSelectionHandles();
		getConnectionFigure().setLineWidth(2);
	}

	@Override
	protected void removeSelectionHandles() {
		getConnectionFigure().setLineWidth(0);
		super.removeSelectionHandles();
	}
}
