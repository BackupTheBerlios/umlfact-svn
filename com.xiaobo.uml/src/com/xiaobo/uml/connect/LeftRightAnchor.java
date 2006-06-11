package com.xiaobo.uml.connect;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;

/**
 * 
 * @author xiaobo
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class LeftRightAnchor extends ChopboxAnchor {

	public LeftRightAnchor(IFigure owner) {
		super(owner);
	}

	public Point getLocation(Point reference) {
		Point p;
		p = getOwner().getBounds().getCenter();
		getOwner().translateToAbsolute(p);
		if (reference.x < p.x) {
			p = getOwner().getBounds().getLeft();
		} else {
			p = getOwner().getBounds().getRight();
		}
		getOwner().translateToAbsolute(p);
		return p;
	}
}
