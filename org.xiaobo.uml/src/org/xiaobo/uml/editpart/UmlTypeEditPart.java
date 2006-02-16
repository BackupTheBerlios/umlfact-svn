package org.xiaobo.uml.editpart;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.xiaobo.uml.editpolicy.UmlTypeFlowLayoutEditPolicy;
import org.xiaobo.uml.figure.UmlTypeFigure;
import org.xiaobo.uml.model.UmlType;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright ? 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlTypeEditPart extends UmlElementEditPart {

	protected IFigure createFigure() {
		return new UmlTypeFigure((UmlType) this.getModel());
	}

	protected void createEditPolicies() {
		super.createEditPolicies();
		this.installEditPolicy(EditPolicy.CONTAINER_ROLE,
				new UmlTypeFlowLayoutEditPolicy());
	}

	protected void refreshVisuals() {
		super.refreshVisuals();
		Dimension size = this.getFigure().getPreferredSize();
		Point p = ((UmlType) getModel()).getLocation();
		((GraphicalEditPart) this.getParent()).setLayoutConstraint(this, this
				.getFigure(), new Rectangle(p, size));
	}

	public IFigure getContentPane() {
		return ((UmlTypeFigure) getFigure()).getContainerFigure();
	}
}