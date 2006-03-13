package com.xiaobo.uml.parts;

import org.eclipse.draw2d.IFigure;

import com.xiaobo.uml.figure.MemberFigure;

/**
 * 
 * @author xiaobo
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class MemberPart extends NamedElementPart {

	protected IFigure createFigure() {
		return new MemberFigure();
	}
}
