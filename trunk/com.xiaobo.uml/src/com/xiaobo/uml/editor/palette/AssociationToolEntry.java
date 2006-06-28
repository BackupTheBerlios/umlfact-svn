package com.xiaobo.uml.editor.palette;

import org.eclipse.gef.palette.ConnectionCreationToolEntry;

import com.xiaobo.uml.IIconConstants;
import com.xiaobo.uml.UmlPlugin;
import com.xiaobo.uml.factories.UmlCreationFactory;
import com.xiaobo.uml.model.Association;

/**
 * 
 * @author Xiaobo Sun. Created on 19.06.2006.
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class AssociationToolEntry extends ConnectionCreationToolEntry {

	public AssociationToolEntry() {
		super("Association", "Create a Association", new UmlCreationFactory(
				Association.class), UmlPlugin
				.getImageDescriptor(IIconConstants.ASSOCIATION), UmlPlugin
				.getImageDescriptor(IIconConstants.ASSOCIATION));
	}
}
