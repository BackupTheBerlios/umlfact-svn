package org.xiaobo.uml.editor.palette;

import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright ? 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class PaletteFactory {

	private PaletteRoot root;

	private PaletteDrawer defaultTools;

	private PaletteDrawer dbTools;

	private static PaletteFactory instance = null;

	private PaletteFactory() {
	}

	public static PaletteFactory INSTANCE() {
		if (instance == null)
			instance = new PaletteFactory();
		return instance;
	}

	public PaletteRoot createPaletteRoot() {
		// if(root != null) return root;

		root = new PaletteRoot();
		root.add(createDefaultToolBox());
		root.add(createDbToolBox());
		return root;
	}

	private PaletteDrawer createDefaultToolBox() {
		defaultTools = new PaletteDrawer("Default tools");

		defaultTools.add(new SelectionToolEntry());

		return defaultTools;
	}

	private PaletteDrawer createDbToolBox() {
		dbTools = new PaletteDrawer("Uml tools");

		dbTools.add(new UmlTypeToolEntry());
		dbTools.add(new UmlMemberToolEntry());
		dbTools.add(new UmlConnectionToolEntry());

		return dbTools;
	}
}
