package com.xiaobo.uml.figure;

import com.xiaobo.uml.IIconConstants;
import com.xiaobo.uml.UmlPlugin;

/**
 * 
 * @author xiaobo
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class AttributeFigure extends LabeledFigure {

	public AttributeFigure() {
		setBorder(null);
		setBackgroundColor(ColorFactory.getMemberColor());
		getLabel().setIcon(UmlPlugin.getImage(IIconConstants.ATTRIBUTE_ICON));
		getLabel().setTooltipText("Attribute");
	}
}
