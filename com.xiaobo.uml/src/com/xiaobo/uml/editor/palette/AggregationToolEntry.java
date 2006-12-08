package com.xiaobo.uml.editor.palette;

import org.eclipse.gef.palette.ConnectionCreationToolEntry;

import com.xiaobo.uml.IIconConstants;
import com.xiaobo.uml.UmlPlugin;
import com.xiaobo.uml.model.Aggregation;
import com.xiaobo.uml.model.factory.UmlCreationFactory;

/**
 * 
 * @author xiaobo
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class AggregationToolEntry extends ConnectionCreationToolEntry {

	public AggregationToolEntry() {
		super("Aggregation", "Create a Aggregation", new UmlCreationFactory(
				Aggregation.class), UmlPlugin
				.getImageDescriptor(IIconConstants.AGGREGATION), UmlPlugin
				.getImageDescriptor(IIconConstants.AGGREGATION));
	}

}
