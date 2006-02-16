package org.xiaobo.uml.editor.palette;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.tools.ConnectionCreationTool;
import org.xiaobo.uml.IIconConstants;
import org.xiaobo.uml.UmlPlugin;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlConnectionToolEntry extends CreationToolEntry {

	public UmlConnectionToolEntry() {
		super("Connection", "create connection", null, UmlPlugin
				.getImageDescriptor(IIconConstants.CONNECTION_ICON), null);
	}

	public Tool createTool() {
		return new ConnectionCreationTool(factory);
	}
}
