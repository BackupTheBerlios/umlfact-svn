package com.xiaobo.uml;


import com.xiaobo.uml.xml.*;



import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;

import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;


public class UmlSaveToXml implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;


	// private UmlClassDiagramNewWizard uu;

	public void run(IAction action) {

		
		MessageDialog.openInformation(window.getShell(), "Save into XML","Ok.saved");
		DOMWriter domWriter = new DOMWriter();
		try {
			domWriter.WriteToXml("25.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

	public void selectionChanged(IAction action, ISelection selection) {
	}

	public void dispose() {
	}
	
	
	

}
