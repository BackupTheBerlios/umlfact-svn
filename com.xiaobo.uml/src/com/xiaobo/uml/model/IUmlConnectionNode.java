package com.xiaobo.uml.model;

import java.util.List;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public interface IUmlConnectionNode extends IUmlElement {

	public void addOut(IUmlConnection out);

	public void addIn(IUmlConnection in);

	public List getOuts();

	public List getIns();

	public void removeIn(IUmlConnection con);

	public void removeOut(IUmlConnection con);
}
