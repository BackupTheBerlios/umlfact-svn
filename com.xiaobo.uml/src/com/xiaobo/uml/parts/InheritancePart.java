package com.xiaobo.uml.parts;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.swt.SWT;

import com.xiaobo.uml.figure.InheritanceFigure;
import com.xiaobo.uml.model.Inheritance;

/**
 * 
 * @author xiaobo.
 * 
 * Copyright 2006 by Xiaobo Sun. All rights reserved.
 * 
 */
public class InheritancePart extends ConnectionPart {

	@Override
	protected IFigure createFigure() {
		PolylineConnection conn = new InheritanceFigure();
		conn.setConnectionRouter(new BendpointConnectionRouter());
		return conn;
	}

	@Override
	protected void refreshVisuals() {
		super.refreshVisuals();
		Inheritance model = (Inheritance) getModel();
		PolylineConnection conn = (PolylineConnection) getFigure();
		if (model.isImpl()) {
			conn.setLineStyle(SWT.LINE_DASHDOTDOT);
		} else {
			conn.setLineStyle(SWT.LINE_SOLID);
		}
	}
}
