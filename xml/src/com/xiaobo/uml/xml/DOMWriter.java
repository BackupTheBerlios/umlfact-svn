package com.xiaobo.uml.xml;
import com.xiaobo.uml.model.*;


import java.io.*;

import org.w3c.dom.*;
//import org.xml.sax.SAXException;
import org.apache.xerces.dom.DOMImplementationImpl;
import org.apache.xerces.parsers.DOMParser;

public class DOMWriter {
	public void typeToXml(Type type,String outputFilename)throws Exception {	
		System.out.println("let me know if you can here of typeXML");
		File outputFile = new File(outputFilename);
		Document doc;
		Element root;
		if (!outputFile.exists()) {
			System.out.println("file not exist!");
			DOMImplementation domImpl=new DOMImplementationImpl();
			doc=domImpl.createDocument(null,"item",null);
			root=doc.getDocumentElement();		
			//input id
			root.setAttribute("id","root");			
			//doc.appendChild(root);
			Element edbs = type.toXml(doc);
			root.appendChild(edbs);
		} else {
			System.out.println("file exist!");
			DOMParser parser = new DOMParser();
			parser.parse(outputFilename);
			doc = parser.getDocument();
			//Element last=doc.getLastChild();
			root=doc.getDocumentElement();
			Element edbs = type.toXml(doc);
			root.appendChild(edbs);
			
		}
		DOMSerializer serializer = new DOMSerializer();
		serializer.serialize(doc, outputFile);	
	}
}
