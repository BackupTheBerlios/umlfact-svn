package com.xiaobo.uml;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;



public class UlmSaveToXml implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;

	// private UmlClassDiagramNewWizard uu;

	public void run(IAction action) {

		MessageDialog.openInformation(window.getShell(), "Save into XML",
				"Ok.saved");

	}

	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

	public void selectionChanged(IAction action, ISelection selection) {
	}

	public void dispose() {
	}



	

}
