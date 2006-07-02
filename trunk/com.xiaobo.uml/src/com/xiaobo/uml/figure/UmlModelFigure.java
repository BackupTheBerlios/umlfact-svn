package com.xiaobo.uml.figure;

import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlModelFigure extends FreeformLayer {

	public UmlModelFigure() {
		setLayoutManager(new FreeformLayout());
	}
}
