package com.xiaobo.uml.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;

import com.xiaobo.uml.figure.AttributeFigure;

/**
 * 
 * @author xiaobo
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class AttributePart extends NamedElementPart {

	@Override
	protected IFigure createFigure() {
		AttributeFigure figure = new AttributeFigure();
		Label label = figure.getLabel();
		Font f = new Font(null, "Arial", 8, SWT.NORMAL);
		label.setFont(f);
		return figure;
	}
}
