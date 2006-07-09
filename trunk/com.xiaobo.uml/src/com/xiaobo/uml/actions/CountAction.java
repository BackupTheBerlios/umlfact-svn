package com.xiaobo.uml.actions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
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
			if (element instanceof IParent) {
				countLines((IParent) element);
			}
			MessageBox dialog = new MessageBox(PlatformUI.getWorkbench()
					.getDisplay().getActiveShell(), SWT.OK
					| SWT.ICON_INFORMATION);
			dialog.setText("Line Counter by xiaobo");
			dialog.setMessage("line counter: " + counter);
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

	private void countLines(IParent parent) throws CoreException, IOException {
		IJavaElement[] children;
		children = parent.getChildren();
		for (int i = 0; i < children.length; i++) {
			if (children[i] instanceof ICompilationUnit) {
				IFile ifile = (IFile) children[i].getResource();
				File f = ifile.getLocation().toFile();

				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				while (br.readLine() != null) {
					counter++;
				}
			} else if (children[i] instanceof IParent) {
				countLines((IParent) children[i]);
			}
		}
	}
}