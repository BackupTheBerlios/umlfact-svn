package com.xiaobo.uml.model;

import java.util.List;

import org.eclipse.draw2d.geometry.Point;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public interface IUmlConnection extends IUmlElement {

	public IUmlConnectionNode getSource();

	public void setSource(IUmlConnectionNode source);

	public IUmlConnectionNode getTarget();

	public void setTarget(IUmlConnectionNode target);

	public List getBendpoints();

	public void addBendpoints(int index, Point point);

	public void removeBendpoints(int index);

	public void replaceBendpoints(int index, Point point);

}
