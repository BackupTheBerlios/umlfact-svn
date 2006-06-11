package com.xiaobo.uml.parts;

import org.eclipse.draw2d.IFigure;

import com.xiaobo.uml.figure.MethodFigure;

/**
 * 
 * @author xiaobo
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class MethodPart extends NamedElementPart {

	protected IFigure createFigure() {
		return new MethodFigure();
	}

}
