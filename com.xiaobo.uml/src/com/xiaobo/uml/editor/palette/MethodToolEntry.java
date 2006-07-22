package com.xiaobo.uml.editor.palette;

import org.eclipse.gef.palette.CreationToolEntry;

import com.xiaobo.uml.IIconConstants;
import com.xiaobo.uml.UmlPlugin;
import com.xiaobo.uml.model.Method;
import com.xiaobo.uml.model.factory.UmlCreationFactory;

/**
 * 
 * @author xiaobo
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class MethodToolEntry extends CreationToolEntry {

	public MethodToolEntry() {
		super("Method", "create a method", new UmlCreationFactory(
				Method.class), UmlPlugin
				.getImageDescriptor(IIconConstants.METHOD_ICON), null);
	}
}
