package org.xiaobo.uml.editor.palette;

import org.eclipse.gef.palette.CreationToolEntry;
import org.xiaobo.uml.IIconConstants;
import org.xiaobo.uml.UmlPlugin;
import org.xiaobo.uml.model.UmlType;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright ? 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlTypeToolEntry extends CreationToolEntry {

	public UmlTypeToolEntry() {
		super("Type", "create a type", new UmlCreationFactory(UmlType.class),
				UmlPlugin.getImageDescriptor(IIconConstants.TYPE_ICON), null);
	}
}
