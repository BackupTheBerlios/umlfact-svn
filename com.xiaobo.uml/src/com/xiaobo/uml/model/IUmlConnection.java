package com.xiaobo.uml.model;

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
}
