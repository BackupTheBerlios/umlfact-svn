package com.xiaobo.uml.model;

import org.eclipse.draw2d.geometry.Point;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class PositionableElement extends NamedElement implements
		IPositionableElement {

	private static final long serialVersionUID = 1L;

	private static final String LOCATION_PROP = "Farm::Location";

	private Point location = new Point(10, 10);

	public void setLocation(Point point) {
		if (point != null) {
			location = point;
			firePropertyChange(LOCATION_PROP);
		}
	}

	public Point getLocation() {
		return location;
	}

}
