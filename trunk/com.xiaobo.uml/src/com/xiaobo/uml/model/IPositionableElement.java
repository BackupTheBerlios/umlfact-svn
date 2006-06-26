package com.xiaobo.uml.model;

import org.eclipse.draw2d.geometry.Point;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public interface IPositionableElement extends INamedElement {

	public Point getLocation();

	public void setLocation(Point point);
}
