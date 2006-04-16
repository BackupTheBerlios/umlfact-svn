package com.xiaobo.uml.editor.palette;

import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.requests.SimpleFactory;

import com.xiaobo.uml.IIconConstants;
import com.xiaobo.uml.UmlPlugin;
import com.xiaobo.uml.model.Inheritance;

/**
 * 
 * @author xiaobo
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class InheritanceToolEntry extends ConnectionCreationToolEntry {

	public InheritanceToolEntry() {
		super("Inheritance", "Create a Inheritance", new SimpleFactory(
				Inheritance.class), UmlPlugin
				.getImageDescriptor(IIconConstants.ARROW_ICON), UmlPlugin
				.getImageDescriptor(IIconConstants.ARROW_ICON));
	}
}
