package org.xiaobo.uml.editor;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright ? 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class LeftOrRightAnchor extends ChopboxAnchor {
	public LeftOrRightAnchor(IFigure owner) {
		super(owner);
	}

	public Point getLocation(Point reference) {
		Point p;
		p = getOwner().getBounds().getCenter();
		getOwner().translateToAbsolute(p);
		if (reference.x < p.x) {
			p = getOwner().getBounds().getLeft();
			p.x -= 8;
		} else {
			p = getOwner().getBounds().getRight();
			p.x += 8;
		}
		getOwner().translateToAbsolute(p);
		return p;
	}
}
