package org.xiaobo.uml.editor;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class TextPropertyLabelProvider extends LabelProvider {
	public static TextPropertyLabelProvider INSTANCE() {
		if (instance == null)
			instance = new TextPropertyLabelProvider();
		return instance;
	}

	private static TextPropertyLabelProvider instance = null;

	public Image getImage(Object element) {
		return null;
	}
}
