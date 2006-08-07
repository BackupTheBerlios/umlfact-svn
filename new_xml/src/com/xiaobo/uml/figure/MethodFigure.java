package com.xiaobo.uml.figure;

import com.xiaobo.uml.IIconConstants;
import com.xiaobo.uml.UmlPlugin;

/**
 * 
 * @author xiaobo
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class MethodFigure extends LabeledFigure {

	public MethodFigure() {
		setBorder(null);
		setBackgroundColor(ColorFactory.getMemberColor());
		getLabel().setIcon(UmlPlugin.getImage(IIconConstants.METHOD_ICON));
		getLabel().setTooltipText("Method");
	}
}
