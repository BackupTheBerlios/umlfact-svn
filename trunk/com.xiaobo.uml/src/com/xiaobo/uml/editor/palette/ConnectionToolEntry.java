package com.xiaobo.uml.editor.palette;

import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.requests.SimpleFactory;

import com.xiaobo.uml.IIconConstants;
import com.xiaobo.uml.UmlPlugin;
import com.xiaobo.uml.model.ConnectionModel;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */

public class ConnectionToolEntry extends ConnectionCreationToolEntry {

	public ConnectionToolEntry() {
		super("Connection", "Create a Connection", new SimpleFactory(
				ConnectionModel.class), UmlPlugin
				.getImageDescriptor(IIconConstants.ARROW_ICON), UmlPlugin
				.getImageDescriptor(IIconConstants.ARROW_ICON));
	}

}
