package com.xiaobo.uml.connect;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * 
 * @author xiaobo
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class TopBottomAnchor extends AbstractConnectionAnchor {

	public TopBottomAnchor(IFigure source) {
		super(source);
	}

	public Point getLocation(Point reference) {
		Rectangle r = getOwner().getBounds().getCopy();
		getOwner().translateToAbsolute(r);
		int off = r.width / 2;
		if (r.y < reference.y) {
			return r.getBottomLeft().translate(off, 4);
		}
		return r.getTopLeft().translate(off, -4);
	}
}
