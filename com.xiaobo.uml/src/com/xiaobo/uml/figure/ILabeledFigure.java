package com.xiaobo.uml.figure;

import org.eclipse.draw2d.IFigure;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public interface ILabeledFigure extends IFigure {

	public EditableLabel getLabel();

	public IFigure getContentPane();
}
