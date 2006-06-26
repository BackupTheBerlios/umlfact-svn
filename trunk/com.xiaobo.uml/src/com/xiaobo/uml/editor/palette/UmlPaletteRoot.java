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

	private PaletteDrawer componentsTools;

	private PaletteDrawer connectionTools;

	public UmlPaletteRoot() {
		add(createDefaultToolBox());
		add(createConnectionDrawer());
		add(createComponentDrawer());
	}

	private PaletteDrawer createDefaultToolBox() {
		defaultTools = new PaletteDrawer("Default tools");
		defaultTools.add(new SelectionToolEntry());
		return defaultTools;
	}

	private PaletteDrawer createConnectionDrawer() {
		connectionTools = new PaletteDrawer("Connection tools");
		connectionTools.add(new InheritanceToolEntry());
		connectionTools.add(new AggregationToolEntry());
		connectionTools.add(new AssociationToolEntry());
		return connectionTools;
	}

	private PaletteDrawer createComponentDrawer() {
		componentsTools = new PaletteDrawer("Components tools");
		componentsTools.add(new TypeToolEntry());
		// componentsTools.add(new CompartmentToolEntry());
		// componentsTools.add(new MemberToolEntry());
		componentsTools.add(new AttributeToolEntry());
		componentsTools.add(new MethodToolEntry());
		return componentsTools;
	}
}
