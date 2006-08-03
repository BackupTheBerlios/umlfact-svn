package com.xiaobo.uml.model;
import com.xiaobo.uml.xml.*;

import java.util.ArrayList;
import java.util.List;

import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.apache.xerces.dom.DOMImplementationImpl;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.DOMImplementation;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlModel extends UmlElement implements IUmlContainer {

	private static final long serialVersionUID = 1L;

	private static final String CHILD_PROP = "child";

	private List types = new ArrayList();

	public List getChildren() {
		return types;
	}

	public void addChild(IUmlElement element) {
		if (!(element instanceof Type)) {
			throw new IllegalArgumentException();
		}
		types.add(element);
		firePropertyChange(CHILD_PROP);
	}

	public void addChild(IUmlElement element, int index) {
		if (!(element instanceof Type)) {
			throw new IllegalArgumentException();
		}
		types.add(index, element);
		firePropertyChange(CHILD_PROP);
	}

	public void removeChild(IUmlElement element) {
		if (!(element instanceof Type)) {
			throw new IllegalArgumentException();
		}
		types.remove(element);
		firePropertyChange(CHILD_PROP);
	}
	
	public void WriteToXml(String outputFilename)throws Exception{
		System.out.println("umlModel toXml");
		
		File outputFile = new File(outputFilename);
		Document doc;
		Element root;
		if (!outputFile.exists()) {
			System.out.println("file not exist!");
			DOMImplementation domImpl=new DOMImplementationImpl();
			doc=domImpl.createDocument(null,"item",null);
			root=doc.getDocumentElement();		
			//input id
			root.setAttribute("classId",serialVersionUID+"");			
			//doc.appendChild(root);
			
		} else {
			System.out.println("file exist!");
			DOMParser parser = new DOMParser();
			parser.parse(outputFilename);
			doc = parser.getDocument();
			//Element last=doc.getLastChild();
			root=doc.getDocumentElement();
		}
		for(int i=0;i<types.size();i++){
			Element edbs = ((Type)types.get(i)).toXml(doc);
			root.appendChild(edbs);
		}
		

		DOMSerializer serializer = new DOMSerializer();
		serializer.serialize(doc, outputFile);
	}
	
}
