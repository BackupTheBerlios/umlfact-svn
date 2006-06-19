package com.xiaobo.uml.connect;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;

public class AwayAnchor extends ChopboxAnchor {

	public AwayAnchor(IFigure source) {
		super(source);
	}

	public Point getLocation(Point reference) {
		Point p = super.getLocation(reference);
		if (p.x >= getBox().getRight().x) {
			p.x += 6;
		} else if (p.x <= getBox().getLeft().x) {
			p.x -= 6;
		} else if (p.y <= getBox().getTop().y) {
			p.y -= 6;
		} else if (p.y >= getBox().getBottom().y) {
			p.y += 6;
		}
		return p;
	}

}
