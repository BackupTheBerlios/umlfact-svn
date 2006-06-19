package com.xiaobo.uml.parts;

import org.eclipse.draw2d.IFigure;

import com.xiaobo.uml.figure.AttributeFigure;

/**
 * 
 * @author xiaobo
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class AttributePart extends NamedElementPart {

	protected IFigure createFigure() {
		return new AttributeFigure();
	}
}
