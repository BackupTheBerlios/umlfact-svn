package com.xiaobo.uml.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.xiaobo.uml.UmlPlugin;
import com.xiaobo.uml.editor.UmlEditor;

public class DOMWriter {

	public void writeToXml(String outputFilename) throws Exception {
		System.out.println("umlModel toXml");
		File outputFile = new File(outputFilename);

		Document doc;
		Element root;
		UmlEditor editor = (UmlEditor) UmlPlugin.getDefault().getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		
		outputFile.delete();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		doc = builder.newDocument();;
		root = (Element) doc.createElement("umlModel");
		root.setAttribute("umlModelId", "ModelId");

		Element edbs = editor.getModel().toDom(doc);
		root.appendChild(edbs);
		doc.appendChild(root);


		DOMSerializer serializer = new DOMSerializer();
		serializer.serialize(doc, outputFile);
	}
}
