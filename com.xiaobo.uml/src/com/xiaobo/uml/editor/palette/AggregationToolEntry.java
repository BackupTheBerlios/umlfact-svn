package com.xiaobo.uml.editor.palette;

import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.requests.SimpleFactory;

import com.xiaobo.uml.IIconConstants;
import com.xiaobo.uml.UmlPlugin;
import com.xiaobo.uml.model.Aggregation;

/**
 * 
 * @author xiaobo
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class AggregationToolEntry extends ConnectionCreationToolEntry {

	public AggregationToolEntry() {
		super("Aggregation", "Create a Aggregation", new SimpleFactory(
				Aggregation.class), UmlPlugin
				.getImageDescriptor(IIconConstants.ARROW_ICON), UmlPlugin
				.getImageDescriptor(IIconConstants.ARROW_ICON));
	}

}
