package com.xiaobo.uml.editor.palette;

import org.eclipse.gef.palette.ConnectionCreationToolEntry;

import com.xiaobo.uml.IIconConstants;
import com.xiaobo.uml.UmlPlugin;
import com.xiaobo.uml.factories.UmlCreationFactory;
import com.xiaobo.uml.model.Inheritance;

/**
 * 
 * @author xiaobo
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class InheritanceToolEntry extends ConnectionCreationToolEntry {

	public InheritanceToolEntry() {
		super("Inheritance", "Create a Inheritance", new UmlCreationFactory(
				Inheritance.class), UmlPlugin
				.getImageDescriptor(IIconConstants.INHERITANCE), UmlPlugin
				.getImageDescriptor(IIconConstants.INHERITANCE));
	}
}
