package com.xiaobo.uml.parts;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;

import com.xiaobo.uml.model.IPositionableElement;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */

public abstract class PositionableElementPart extends NamedElementPart {

	protected void refreshVisuals() {
		super.refreshVisuals();
		if (getParent() != null) {
			Rectangle bounds = new Rectangle(
					((IPositionableElement) getModel()).getLocation(),
					getFigure().getPreferredSize());
			((GraphicalEditPart) getParent()).setLayoutConstraint(this,
					getFigure(), bounds);
		}
	}
}
