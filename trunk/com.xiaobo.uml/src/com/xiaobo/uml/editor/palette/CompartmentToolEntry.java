package com.xiaobo.uml.editor.palette;

import org.eclipse.gef.palette.CreationToolEntry;

import com.xiaobo.uml.IIconConstants;
import com.xiaobo.uml.UmlPlugin;
import com.xiaobo.uml.factories.UmlCreationFactory;
import com.xiaobo.uml.model.Compartment;

/**
 * 
 * @author xiaobo
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class CompartmentToolEntry extends CreationToolEntry {

	public CompartmentToolEntry() {
		super("Compartment", "create a compartment", new UmlCreationFactory(
				Compartment.class), UmlPlugin
				.getImageDescriptor(IIconConstants.COMPARTMENT_ICON), null);
	}

}
