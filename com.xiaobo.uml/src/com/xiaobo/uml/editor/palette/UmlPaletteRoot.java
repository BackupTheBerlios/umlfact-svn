package com.xiaobo.uml.editor.palette;

import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlPaletteRoot extends PaletteRoot {

	private PaletteDrawer defaultTools;

	private PaletteDrawer umlTools;

	public UmlPaletteRoot() {
		add(createDefaultToolBox());
		add(createUmlToolBox());
	}

	private PaletteDrawer createDefaultToolBox() {
		defaultTools = new PaletteDrawer("Default tools");
		defaultTools.add(new SelectionToolEntry());
		return defaultTools;
	}

	private PaletteDrawer createUmlToolBox() {
		umlTools = new PaletteDrawer("Uml tools");
		umlTools.add(new TypeToolEntry());
		umlTools.add(new ConnectionToolEntry());
		umlTools.add(new CompartmentToolEntry());
		umlTools.add(new MemberToolEntry());
		return umlTools;
	}
}
