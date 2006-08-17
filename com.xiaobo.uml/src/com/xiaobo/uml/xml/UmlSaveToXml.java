package com.xiaobo.uml.xml;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;


public class UmlSaveToXml implements IWorkbenchWindowActionDelegate {

	private static final String[] FILTER_NAMES = { "XML Files (*.xml)" };

	private static final String[] FILTER_EXTS = { "*.xml" };

	private IWorkbenchWindow window;

	public void run(IAction action) {
		DOMWriter domWriter = new DOMWriter();
		try {
			FileDialog dlg = new FileDialog(window.getShell(), SWT.SINGLE);
			dlg.setFilterNames(FILTER_NAMES);
			dlg.setFilterExtensions(FILTER_EXTS);
			String fn = dlg.open();
			domWriter.writeToXml(fn);
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
