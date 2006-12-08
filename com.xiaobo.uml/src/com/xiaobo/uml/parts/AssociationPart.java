package com.xiaobo.uml.parts;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.IFigure;

import com.xiaobo.uml.figure.AssociationFigure;
import com.xiaobo.uml.model.Association;

/**
 * 
 * @author Xiaobo Sun. Created on 19.06.2006.
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class AssociationPart extends ConnectionPart {

	@Override
	protected IFigure createFigure() {
		Association asso = (Association) getModel();
		AssociationFigure conn = new AssociationFigure(asso.getSourceString(),
				asso.getTargetString(), asso.isSourceNavigation(), asso
						.isTargetNavigation());
		conn.setConnectionRouter(new BendpointConnectionRouter());
		return conn;
	}

	@Override
	protected void refreshVisuals() {
		super.refreshVisuals();
		Association asso = (Association) getModel();
		AssociationFigure conn = (AssociationFigure) getFigure();
		conn.setSourceString(asso.getSourceString());
		conn.setTargetString(asso.getTargetString());
		conn.setSourceNavigation(asso.isSourceNavigation());
		conn.setTargetNavigation(asso.isTargetNavigation());
	}
}
