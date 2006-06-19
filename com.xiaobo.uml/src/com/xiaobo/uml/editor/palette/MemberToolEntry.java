package com.xiaobo.uml.editor.palette;

import org.eclipse.gef.palette.CreationToolEntry;

import com.xiaobo.uml.IIconConstants;
import com.xiaobo.uml.UmlPlugin;
import com.xiaobo.uml.factories.UmlCreationFactory;
import com.xiaobo.uml.model.MemberModel;

/**
 * 
 * @author xiaobo
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class MemberToolEntry extends CreationToolEntry {
	public MemberToolEntry() {
		super("Member", "create a member", new UmlCreationFactory(
				MemberModel.class), UmlPlugin
				.getImageDescriptor(IIconConstants.MEMBER_ICON), null);
	}
}
