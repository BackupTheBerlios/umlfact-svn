package org.xiaobo.uml.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.editparts.FreeformGraphicalRootEditPart;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.parts.GraphicalEditorWithPalette;
import org.xiaobo.uml.editor.palette.PaletteFactory;
import org.xiaobo.uml.editpart.UmlEditPartFactory;
import org.xiaobo.uml.model.UmlModel;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright ? 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlEditor extends GraphicalEditorWithPalette {

	public UmlEditor() {
		this.setEditDomain(new DefaultEditDomain(this));
	}

	protected PaletteRoot getPaletteRoot() {
		return PaletteFactory.INSTANCE().createPaletteRoot();
	}

	protected void initializeGraphicalViewer() {
		UmlModel schema = new UmlModel();
		this.getGraphicalViewer().setContents(schema);
	}

	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		GraphicalViewer viewer = this.getGraphicalViewer();
		viewer.setRootEditPart(new FreeformGraphicalRootEditPart());
		viewer.setEditPartFactory(new UmlEditPartFactory());
	}

	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
	}

	public void doSaveAs() {
		// TODO Auto-generated method stub
	}

	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}
}
