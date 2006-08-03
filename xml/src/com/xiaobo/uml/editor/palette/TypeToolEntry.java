package com.xiaobo.uml.editor.palette;

import org.eclipse.gef.palette.CreationToolEntry;

import com.xiaobo.uml.IIconConstants;
import com.xiaobo.uml.UmlPlugin;
import com.xiaobo.uml.factories.UmlCreationFactory;
import com.xiaobo.uml.model.Type;


/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class TypeToolEntry extends CreationToolEntry {

	public TypeToolEntry() {
		super("Type", "create a type", new UmlCreationFactory(Type.class),
				UmlPlugin.getImageDescriptor(IIconConstants.TYPE_ICON), null);
		System.out.println("here is TypeToolEntry");
		
	}
}
