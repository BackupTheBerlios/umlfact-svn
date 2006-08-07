package com.xiaobo.uml;

import com.xiaobo.uml.xml.*;



import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;

import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;



public class LoadFromXml  implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;
	//private CreationPage page1;
	// private UmlClassDiagramNewWizard uu;
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}
	
	public void run(IAction action) {
	
		//addPages();
		//System.out.println("filename:"+page1.getFileName());
		
		DOMLoader domLoader = new DOMLoader();
		try {
			domLoader.loadFormXml("20.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
		MessageDialog.openInformation(window.getShell(), "Load From Xml","Ok.loaded");
	}
	
	public void selectionChanged(IAction action, ISelection selection) {
	}

	public void dispose() {
	}
/*
	
	public void addPages() {
		addPage(page1);
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		page1 = new CreationPage(workbench, selection);
	}

	public boolean performFinish() {
		return page1.finish();
	}

	
	
	private class CreationPage extends WizardNewFileCreationPage {

		private static final String DEFAULT_EXTENSION = ".xml";

		private final IWorkbench workbench;

		CreationPage(IWorkbench workbench, IStructuredSelection selection) {
			super("UmlCreationPage1", selection);
			this.workbench = workbench;
			setTitle("Create a new " + DEFAULT_EXTENSION + " file");
			setDescription("Create a new " + DEFAULT_EXTENSION + " file");
		}

		public void createControl(Composite parent) {
			super.createControl(parent);
			setFileName("UmlClassDiagram" + DEFAULT_EXTENSION);
			setPageComplete(validatePage());
		}

		private Object createDefaultContent() {
			return new UmlModel();
		}

		boolean finish() {
			IFile newFile = createNewFile();

			IWorkbenchPage page = workbench.getActiveWorkbenchWindow()
					.getActivePage();
			if (newFile != null && page != null) {
				try {
					IDE.openEditor(page, newFile, true);
				} catch (PartInitException e) {
					e.printStackTrace();
					return false;
				}
			}
			return true;
		}

		protected InputStream getInitialContents() {
			ByteArrayInputStream bais = null;
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos);
				oos.writeObject(createDefaultContent());
				oos.flush();
				oos.close();
				bais = new ByteArrayInputStream(baos.toByteArray());
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			return bais;
		}

		private boolean validateFilename() {
			if (getFileName() == null
					&& getFileName().endsWith(DEFAULT_EXTENSION)) {
				return true;
			}
			setErrorMessage("The 'file' name must end with "
					+ DEFAULT_EXTENSION);
			return false;
		}

		protected boolean validatePage() {
			return super.validatePage() && validateFilename();
		}
	}
*/
}