package com.xiaobo.uml.xml;

import java.io.File;

import org.eclipse.gef.requests.CreationFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.apache.xerces.dom.DOMImplementationImpl;
import org.apache.xerces.parsers.DOMParser;


import com.xiaobo.uml.model.Type;

import org.w3c.dom.DOMImplementation;

public class XmlCreationFactory {
	private Class type;

	static File xmlFile = new File("uu.xml");
	static Document doc;
	static Element root;
	
	public XmlCreationFactory(){
		System.out.println("here is XmlCreationFactory");	
		
	}
	
	public static void creatType(Type type){
		try {
			System.out.println("ceateType Methode");
			if (!xmlFile.exists()) {
				System.out.println("file not exist!");
				DOMImplementation domImpl = new DOMImplementationImpl();
				doc = domImpl.createDocument(null, "item", null);
				root = doc.getDocumentElement();
				// input id
				root.setAttribute("id", "id2");
				// doc.appendChild(root);
				Element edbs = type.toXml(doc);
				root.appendChild(edbs);
			} else {
				System.out.println("file exist!");
				DOMParser parser = new DOMParser();
				parser.parse(xmlFile.toURI().toString());
				doc = parser.getDocument();
				// Element last=doc.getLastChild();
				root = doc.getDocumentElement();
				Element edbs = type.toXml(doc);
				root.appendChild(edbs);
			}

			DOMSerializer serializer = new DOMSerializer();
			serializer.serialize(doc, xmlFile);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	

	public Object getObjectType() {
		return getType();
	}

	public Class getType() {
		return type;
	}

	public void setType(Class type) {
		this.type = type;
	}

}
