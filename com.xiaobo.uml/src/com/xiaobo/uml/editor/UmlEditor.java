package com.xiaobo.uml.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;

import com.xiaobo.uml.editor.palette.UmlFlyoutPreferences;
import com.xiaobo.uml.editor.palette.UmlPaletteRoot;
import com.xiaobo.uml.model.UmlModel;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlEditor extends GraphicalEditorWithFlyoutPalette {

	public UmlEditor() {
		setEditDomain(new DefaultEditDomain(this));
	}

	protected PaletteRoot getPaletteRoot() {
		return new UmlPaletteRoot();
	}

	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		GraphicalViewer viewer = this.getGraphicalViewer();
		viewer.setEditPartFactory(new UmlEditPartFactory());
	}

	protected void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();
		UmlModel model = new UmlModel();
		this.getGraphicalViewer().setContents(model);
	}

	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}

	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	protected FlyoutPreferences getPalettePreferences() {
		return new UmlFlyoutPreferences();
	}

	protected void createActions() {
		super.createActions();
	}

}
