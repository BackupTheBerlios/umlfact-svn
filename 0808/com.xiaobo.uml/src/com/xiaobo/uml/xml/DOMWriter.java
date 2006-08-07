package com.xiaobo.uml.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.xiaobo.uml.editor.UmlEditor;

public class DOMWriter {

	
	public void WriteToXml(String outputFilename)throws Exception{
		System.out.println("umlModel toXml");
		//UmlEditor uml;
		
		File outputFile = new File(outputFilename);
	
        Document doc;
        Element root;		
		if (!outputFile.exists()) {
			System.out.println("file not exist!");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.newDocument();
			//root=doc.getDocumentElement();
			//System.out.println(root.getNodeName());
			root = (Element) doc.createElement("umlModel");	
			root.setAttribute("umlModelId","ModelId");
			Element edbs=UmlEditor.umlModel.toDom(doc);
			root.appendChild(edbs);		
			doc.appendChild(root);
			
		} else {
			System.out.println("file exist!");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
	        doc = builder.parse(outputFile);
	        root = doc.getDocumentElement();
	        Element edbs=UmlEditor.umlModel.toDom(doc);
			root.appendChild(edbs);
			
			
		}
		/*for(int i=0;i<types.size();i++){
			Element edbs = ((Type)types.get(i)).toXml(doc);
			root.appendChild(edbs);
		}*/
		

		DOMSerializer serializer = new DOMSerializer();
		serializer.serialize(doc, outputFile);
	}

}
