package com.xiaobo.uml.figure;

import org.eclipse.draw2d.ToolbarLayout;

import com.xiaobo.uml.IIconConstants;
import com.xiaobo.uml.UmlPlugin;

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
		getLabel().setIcon(UmlPlugin.getImage(IIconConstants.COMPARTMENT_ICON));
		getLabel().setTooltipText("Compartment");
	}

}
