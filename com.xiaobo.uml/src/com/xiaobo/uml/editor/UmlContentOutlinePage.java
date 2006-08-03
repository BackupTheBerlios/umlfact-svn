package com.xiaobo.uml.editor;

import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.gef.ui.parts.SelectionSynchronizer;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.xiaobo.uml.model.UmlModel;

/**
 * 
 * @author Xiaobo Sun. Created on 19.06.2006.
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class UmlContentOutlinePage extends ContentOutlinePage {

	private UmlModel model;

	private EditDomain editDomain;

	private EditPartFactory editPartFactory;

	private SelectionSynchronizer selectionSynchronizer;

	public UmlContentOutlinePage(UmlModel model, EditDomain editDomain,
			EditPartFactory editPartFactory,
			SelectionSynchronizer selectionSynchronizer) {
		super(new TreeViewer());
		setModel(model);
		setEditDomain(editDomain);
		setEditPartFactory(editPartFactory);
		setSelectionSynchronizer(selectionSynchronizer);
	}

	public void createControl(Composite parent) {
		getViewer().createControl(parent);
		getViewer().setEditDomain(getEditDomain());
		getViewer().setEditPartFactory(getEditPartFactory());
		getSelectionSynchronizer().addViewer(getViewer());
		getViewer().setContents(getModel());
	}

	public void dispose() {
		getSelectionSynchronizer().removeViewer(getViewer());
		super.dispose();
	}

	public Control getControl() {
		return getViewer().getControl();
	}

	public UmlModel getModel() {
		return model;
	}

	public void setModel(UmlModel model) {
		this.model = model;
	}

	public SelectionSynchronizer getSelectionSynchronizer() {
		return selectionSynchronizer;
	}

	public void setSelectionSynchronizer(
			SelectionSynchronizer selectionSynchronizer) {
		this.selectionSynchronizer = selectionSynchronizer;
	}

	public EditDomain getEditDomain() {
		return editDomain;
	}

	public void setEditDomain(EditDomain editDomain) {
		this.editDomain = editDomain;
	}

	public EditPartFactory getEditPartFactory() {
		return editPartFactory;
	}

	public void setEditPartFactory(EditPartFactory editPartFactory) {
		this.editPartFactory = editPartFactory;
	}

}
