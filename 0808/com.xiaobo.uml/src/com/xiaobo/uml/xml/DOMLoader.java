package com.xiaobo.uml.xml;

import javax.xml.parsers.*;

import org.w3c.dom.*;

import java.io.*;

import com.xiaobo.uml.model.*;
import com.xiaobo.uml.editor.*;

public class DOMLoader  {


	public void loadFormXml(String outputFilename) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new FileInputStream(outputFilename));
			Element root = doc.getDocumentElement();

			UmlEditor.umlModel = new UmlModel(root);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	


}
