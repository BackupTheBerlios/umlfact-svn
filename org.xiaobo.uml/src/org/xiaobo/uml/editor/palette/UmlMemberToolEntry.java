package org.xiaobo.uml.editor.palette;

import org.eclipse.gef.palette.CreationToolEntry;
import org.xiaobo.uml.IIconConstants;
import org.xiaobo.uml.UmlPlugin;
import org.xiaobo.uml.model.UmlMember;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright ? 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlMemberToolEntry extends CreationToolEntry {

	public UmlMemberToolEntry() {
		super("Member", "create a member", new UmlCreationFactory(
				UmlMember.class), UmlPlugin
				.getImageDescriptor(IIconConstants.MEMBER_ICON), null);
	}
}
