package org.xiaobo.uml.model;

import org.eclipse.draw2d.geometry.Point;

public interface IPositionableElement extends INamedElement {

	public Point getLocation();

	public void setLocation(Point point);
}
