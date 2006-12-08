package com.xiaobo.uml.figure;

import org.eclipse.swt.graphics.Color;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class ColorFactory {

	public static Color getTypeColor() {
		return new Color(null, 255, 255, 206);
	}

	public static Color getCompartmentColor() {
		return getTypeColor();
	}

	public static Color getMemberColor() {
		return getTypeColor();
	}
}
