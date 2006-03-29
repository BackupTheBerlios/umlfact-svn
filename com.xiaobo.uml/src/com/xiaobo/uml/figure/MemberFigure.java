package com.xiaobo.uml.figure;

import org.eclipse.draw2d.MarginBorder;

import com.xiaobo.uml.IIconConstants;
import com.xiaobo.uml.UmlPlugin;

/**
 * 
 * @author xiaobo
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class MemberFigure extends LabeledFigure {
	public MemberFigure() {
		setBorder(new MarginBorder(1));
		setBackgroundColor(ColorFactory.getMemberColor());
		getLabel().setIcon(UmlPlugin.getImage(IIconConstants.MEMBER_ICON));
		getLabel().setTooltipText("Member");
	}
}
