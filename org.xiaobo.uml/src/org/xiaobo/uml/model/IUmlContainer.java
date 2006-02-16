package org.xiaobo.uml.model;

import java.util.List;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public interface IUmlContainer extends IUmlElement {

	public List getChildren();

	public void addChild(IUmlElement element);

	public void addChild(IUmlElement element, int index);

	public void removeChild(IUmlElement element);
}
