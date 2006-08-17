package com.xiaobo.uml.xml;

import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.xiaobo.uml.UmlPlugin;
import com.xiaobo.uml.editor.UmlEditor;
import com.xiaobo.uml.model.UmlModel;

public class DOMLoader {

	public void loadFormXml(String outputFilename) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new FileInputStream(outputFilename));
			Element root = doc.getDocumentElement();
			UmlModel model = new UmlModel(root);

			UmlEditor editor = (UmlEditor) UmlPlugin.getDefault()
					.getWorkbench().getActiveWorkbenchWindow().getActivePage()
					.getActiveEditor();
			editor.setModel(model);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
