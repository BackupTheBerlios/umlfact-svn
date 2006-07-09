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

	public void addBendpoint(int index, Point point);

	public void removeBendpoint(int index);

	public void replaceBendpoint(int index, Point point);

}
