package com.xiaobo.uml.editor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.EventObject;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import com.xiaobo.uml.editor.palette.UmlFlyoutPreferences;
import com.xiaobo.uml.editor.palette.UmlPaletteRoot;
import com.xiaobo.uml.model.UmlModel;
import com.xiaobo.uml.parts.UmlModelPart;
import com.xiaobo.uml.parts.factories.UmlEditPartFactory;
import com.xiaobo.uml.parts.factories.UmlTreePartFactory;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlEditor extends GraphicalEditorWithFlyoutPalette {

	private UmlModel umlModel;

	public UmlEditor() {
		setEditDomain(new DefaultEditDomain(this));
	}

	@Override
	protected PaletteRoot getPaletteRoot() {
		return new UmlPaletteRoot();
	}

	@Override
	protected FlyoutPreferences getPalettePreferences() {
		return new UmlFlyoutPreferences();
	}

	public UmlModel getModel() {
		return umlModel;
	}

	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();

		ScalableFreeformRootEditPart root = new ScalableFreeformRootEditPart();

		getGraphicalViewer().setRootEditPart(root);
		getGraphicalViewer().setEditPartFactory(new UmlEditPartFactory());
	}

	/**
	 * solve the bug at initializing.
	 */
	@Override
	protected void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();
		getGraphicalViewer().setContents(getModel());
		((UmlModelPart) getGraphicalViewer().getRootEditPart().getChildren()
				.get(0)).refresh();

		ScalableFreeformRootEditPart root = (ScalableFreeformRootEditPart) getGraphicalViewer()
				.getRootEditPart();
		Action action1 = new ZoomInAction(root.getZoomManager());
		getActionRegistry().registerAction(action1);
		Action action2 = new ZoomOutAction(root.getZoomManager());
		getActionRegistry().registerAction(action2);
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			createOutputStream(out);
			IFile file = ((IFileEditorInput) getEditorInput()).getFile();
			file.setContents(new ByteArrayInputStream(out.toByteArray()), true,
					false, monitor);
			getCommandStack().markSaveLocation();
		} catch (CoreException ce) {
			ce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@Override
	public void doSaveAs() {
		Shell shell = getSite().getWorkbenchWindow().getShell();
		SaveAsDialog dialog = new SaveAsDialog(shell);
		dialog.setOriginalFile(((IFileEditorInput) getEditorInput()).getFile());
		dialog.open();
		IPath path = dialog.getResult();
		if (path != null) {
			final IFile file = ResourcesPlugin.getWorkspace().getRoot()
					.getFile(path);
			try {
				new ProgressMonitorDialog(shell).run(false, false,
						new WorkspaceModifyOperation() {
							@Override
							public void execute(final IProgressMonitor monitor) {
								try {
									ByteArrayOutputStream out = new ByteArrayOutputStream();
									createOutputStream(out);
									file.create(new ByteArrayInputStream(out
											.toByteArray()), true, monitor);
								} catch (CoreException ce) {
									ce.printStackTrace();
								} catch (IOException ioe) {
									ioe.printStackTrace();
								}
							}
						});
				setInput(new FileEditorInput(file));
				getCommandStack().markSaveLocation();
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			} catch (InvocationTargetException ite) {
				ite.printStackTrace();
			}
		}
	}

	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

	private void createOutputStream(OutputStream os) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(getModel());
		oos.close();
	}

	@Override
	protected void setInput(IEditorInput input) {
		super.setInput(input);
		try {
			IFile file = ((IFileEditorInput) input).getFile();
			ObjectInputStream in = new ObjectInputStream(file.getContents());
			umlModel = (UmlModel) in.readObject();
			in.close();
			setPartName(file.getName());
		} catch (IOException e) {
			handleLoadException(e);
		} catch (CoreException e) {
			handleLoadException(e);
		} catch (ClassNotFoundException e) {
			handleLoadException(e);
		}
	}

	private void handleLoadException(Exception e) {
		System.err.println("** Load failed. Using default model. **");
		e.printStackTrace();
		umlModel = new UmlModel();
	}

	@Override
	protected void createActions() {
		super.createActions();
	}

	@Override
	public Object getAdapter(Class type) {
		if (type == IContentOutlinePage.class) {
			return new UmlContentOutlinePage(getModel(), getEditDomain(),
					new UmlTreePartFactory(), getSelectionSynchronizer());
		} else if (type == ZoomManager.class) {
			return getGraphicalViewer().getProperty(
					ZoomManager.class.toString());
		}
		return super.getAdapter(type);
	}

	@Override
	public void commandStackChanged(EventObject event) {
		firePropertyChange(IEditorPart.PROP_DIRTY);
		super.commandStackChanged(event);
	}
}
