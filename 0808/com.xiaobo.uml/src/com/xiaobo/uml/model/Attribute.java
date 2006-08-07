package com.xiaobo.uml.model;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
/**
 * 
 * @author xiaobo
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class Attribute extends Member {

	private static final long serialVersionUID = 1L;

	public Attribute() {
		setName("-attribute:datatype");
		setDescription("attribute");
	}
	
	public Attribute(Element element) {
		// TODO Auto-generated constructor stub
		System.out.println("attribut has really added!!");
		setName(element.getAttribute("name"));
		setDescription("description");
	}

	public Element toDom(Document doc){
		System.out.println("read Attribut");
		//Document doc = null;
		Element subRoot=doc.createElement("attribute");
		subRoot.setAttribute("name", getName());
		subRoot.setAttribute("description",getDescription() );
		return subRoot;
	}

}
