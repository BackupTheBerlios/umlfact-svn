package com.xiaobo.uml.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ToolbarLayout;

import com.xiaobo.uml.IIconConstants;
import com.xiaobo.uml.UmlPlugin;

/**
 * 
 * @author xiaobo
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class TypeFigure extends Figure {

	private static final int BORDER_SIZE = 1;

	private EditableLabel label;

	private IFigure attributesFigure;

	private IFigure operationsFigure;

	public TypeFigure() {
		label = new EditableLabel();
		attributesFigure = new Figure();
		operationsFigure = new Figure();

		getLabel().setBorder(new LineBorder(ColorConstants.black, BORDER_SIZE));
		getAttributesFigure().setBorder(
				new LineBorder(ColorConstants.black, BORDER_SIZE));
		getOperationsFigure().setBorder(
				new LineBorder(ColorConstants.black, BORDER_SIZE));

		setLayoutManager(new ToolbarLayout(false));
		add(getLabel());
		add(getAttributesFigure());
		add(getOperationsFigure());

		setBorder(new LineBorder(ColorConstants.black, 1));
		setForegroundColor(ColorConstants.black);
		setOpaque(true);

		ToolbarLayout layout = new ToolbarLayout(false);
		layout.setStretchMinorAxis(true);
		layout.setSpacing(0);
		getAttributesFigure().setLayoutManager(layout);
		getOperationsFigure().setLayoutManager(layout);

		setBackgroundColor(ColorFactory.getTypeColor());
		getLabel().setIcon(UmlPlugin.getImage(IIconConstants.TYPE_ICON));
		getLabel().setTooltipText("Type");
	}

	public IFigure getAttributesFigure() {
		return attributesFigure;
	}

	public IFigure getOperationsFigure() {
		return operationsFigure;
	}

	public EditableLabel getLabel() {
		return label;
	}
}
