package com.xiaobo.uml.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.swt.graphics.Color;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */

public class TypeFigure extends LabeledFigure {

	// public TypeFigure() {
	// setBackgroundColor(ColorFactory.getTypeColor());
	// getLabel().setIcon(UmlPlugin.getImage(IIconConstants.TYPE_ICON));
	// getLabel().setTooltipText("Type");
	// }
	public static Color classColor = new Color(null, 255, 255, 206);

	private CompartmentFigure attributeFigure = new CompartmentFigure();

	private CompartmentFigure methodFigure = new CompartmentFigure();

	public TypeFigure() {
		ToolbarLayout layout = new ToolbarLayout();
		setLayoutManager(layout);
		setBorder(new LineBorder(ColorConstants.black, 1));
		setBackgroundColor(classColor);
		setOpaque(true);

		add(attributeFigure);
		add(methodFigure);
	}

	public CompartmentFigure getAttributesCompartment() {
		return attributeFigure;
	}

	public CompartmentFigure getMethodsCompartment() {
		return methodFigure;
	}
}
