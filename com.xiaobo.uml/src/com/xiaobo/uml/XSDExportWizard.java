/**
 * 
 * @author xiaobo. Created on Oct 15, 2006.
 * 
 * Copyright 2006 by Xiaobo Sun. All rights reserved.
 *
 */
package com.xiaobo.uml;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;

import com.xiaobo.uml.editor.UmlEditor;

public class XSDExportWizard extends Wizard implements IExportWizard {

	private static final String TITLE = "Export XSD file";

	private IFinalizableWizardPage page;

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		page = new ExportPage(TITLE, selection);
		setWindowTitle(TITLE);
	}

	@Override
	public void addPages() {
		addPage(page);
	}

	@Override
	public boolean performFinish() {
		if (page.finish()) {
			return true;
		}
		return false;
	}

	private static interface IFinalizableWizardPage extends IWizardPage {
		public boolean finish();
	}

	private static class ExportPage extends WizardPage implements
			IFinalizableWizardPage {

		private Text fileText;

		protected ExportPage(String pageName, IStructuredSelection selection) {
			super(pageName);
		}

		public void createControl(Composite parent) {
			Composite composite = new Composite(parent, SWT.NULL);
			composite.setLayout(new GridLayout(1, false));
			composite.setLayoutData(new GridData(GridData.FILL_BOTH));

			createFileGroup(composite);
			createLabel(composite);
			setControl(composite);
		}

		private void createFileGroup(Composite parent) {
			Composite composite = new Composite(parent, SWT.NONE);
			Button browseButton;

			composite.setLayout(new GridLayout(3, false));
			composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			new Label(composite, SWT.NONE).setText("File:");
			fileText = new Text(composite, SWT.BORDER);
			fileText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			browseButton = new Button(composite, SWT.PUSH);
			browseButton.setText("Browse...");
			browseButton.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					FileDialog dialog = createFileDialog();
					String file = dialog.open();
					if (file != null) {
						fileText.setText(file);
					}
				}
			});
		}
		
		private void createLabel(Composite parent){
			Label label = new Label(parent, SWT.SHADOW_IN|SWT.CENTER);
			label.setText("Please let the UML editor active!");
		}

		protected String getFilePath() {
			return fileText.getText();
		}

		protected FileDialog createFileDialog() {
			FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);
			dialog.setText("Select File");
			return dialog;
		}

		public boolean finish() {
			try {
				// dialog input file path
				String path = getFilePath();
				// write file
				UmlEditor editor = (UmlEditor) UmlPlugin.getDefault()
						.getWorkbench().getActiveWorkbenchWindow()
						.getActivePage().getActiveEditor();
				XSDWriter.getInstance().write(editor.getModel(), path);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}
	}

}
