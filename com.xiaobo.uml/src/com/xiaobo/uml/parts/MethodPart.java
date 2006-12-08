package com.xiaobo.uml.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;

import com.xiaobo.uml.figure.ILabeledFigure;
import com.xiaobo.uml.figure.MethodFigure;
import com.xiaobo.uml.model.Method;

/**
 * 
 * @author xiaobo
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class MethodPart extends NamedElementPart {

	@Override
	protected IFigure createFigure() {
		MethodFigure figure = new MethodFigure();
		Label label = figure.getLabel();
		if (((Method) getModel()).isAbstract()) {
			Font f = new Font(null, "Arial", 8, SWT.ITALIC);
			label.setFont(f);
		} else {
			Font f = new Font(null, "Arial", 8, SWT.NORMAL);
			label.setFont(f);
		}
		return figure;
	}

	@Override
	protected void refreshVisuals() {
		super.refreshVisuals();
		ILabeledFigure figure = (ILabeledFigure) getFigure();
		Label label = figure.getLabel();

		if (((Method) getModel()).isAbstract()) {
			Font f = new Font(null, "Arial", 8, SWT.ITALIC);
			label.setFont(f);
		} else {
			Font f = new Font(null, "Arial", 8, SWT.NORMAL);
			label.setFont(f);
		}
	}
}
