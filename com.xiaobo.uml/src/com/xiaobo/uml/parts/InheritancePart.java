package com.xiaobo.uml.parts;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;

import com.xiaobo.uml.figure.InheritanceFigure;

/**
 * 
 * @author xiaobo.
 * 
 * Copyright 2006 by Xiaobo Sun. All rights reserved.
 * 
 */
public class InheritancePart extends ConnectionPart {

	protected IFigure createFigure() {
		PolylineConnection conn = new InheritanceFigure();
		conn.setConnectionRouter(new BendpointConnectionRouter());
		return conn;
	}
}
