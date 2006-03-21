package com.xiaobo.uml.figure;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.ToolbarLayout;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class CompartmentFigure extends LabeledFigure {

	public CompartmentFigure() {
		ToolbarLayout layout = new ToolbarLayout(false);
		layout.setStretchMinorAxis(true);
		layout.setSpacing(0);
		getContentPane().setLayoutManager(layout);

		setBackgroundColor(ColorFactory.getCompartmentColor());
		getLabel().setTooltipText("Compartment");
		setBorder(null);
	}

	protected void paintFigure(Graphics graphics) {
		super.paintFigure(graphics);
		graphics.drawLine(getBounds().x, getBounds().y, getBounds().x
				+ getBounds().width, getBounds().y);
	}
}
