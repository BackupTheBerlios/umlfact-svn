package com.xiaobo.uml.editor.palette;

import org.eclipse.gef.palette.CreationToolEntry;

import com.xiaobo.uml.IIconConstants;
import com.xiaobo.uml.UmlPlugin;
import com.xiaobo.uml.model.Attribute;
import com.xiaobo.uml.model.factory.UmlCreationFactory;

/**
 * 
 * @author xiaobo
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class AttributeToolEntry extends CreationToolEntry {

	public AttributeToolEntry() {
		super("Attribute", "create a attribute", new UmlCreationFactory(
				Attribute.class), UmlPlugin
				.getImageDescriptor(IIconConstants.ATTRIBUTE_ICON), null);
	}
}
