package com.xiaobo.uml.actions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IParent;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.PlatformUI;

/**
 * 
 * @author xiaobo. Created on Jul 9, 2006.
 * 
 * Copyright 2006 by Xiaobo Sun. All rights reserved.
 * 
 */
public class CountAction implements IActionDelegate {

	private IStructuredSelection selection;

	private int counter;

	public void run(IAction action) {
		try {
			IJavaElement element = (IJavaElement) getSelection()
					.getFirstElement();
			counter = 0;
			countLines(element);
			MessageBox dialog = new MessageBox(PlatformUI.getWorkbench()
					.getDisplay().getActiveShell(), SWT.OK
					| SWT.ICON_INFORMATION);
			dialog.setText("Line Counter by xiaobo");
			dialog.setMessage("You have " + counter
					+ " lines in your java files!");
			dialog.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = (IStructuredSelection) selection;
	}

	protected IStructuredSelection getSelection() {
		return selection;
	}

	/**
	 * actually package manages the code using with the flat format, it's the
	 * reason why model.command package isn't the child of the model package.
	 * 
	 * @param element
	 * @throws CoreException
	 * @throws IOException
	 */
	private void countLines(IJavaElement element) throws CoreException,
			IOException {
		if (element instanceof ICompilationUnit) {
			IFile ifile = (IFile) element.getResource();
			File f = ifile.getLocation().toFile();
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			while (br.readLine() != null) {
				counter++;
			}
		} else if (element instanceof IJavaProject) {
			IJavaElement[] children = ((IParent) element).getChildren();
			if (children[0] instanceof IJavaElement) {
				countLines(children[0]);
			}
		} else if (element instanceof IParent) {
			IJavaElement[] children = ((IParent) element).getChildren();
			for (int i = 0; i < children.length; i++) {
				countLines(children[i]);
			}
		}
	}
}