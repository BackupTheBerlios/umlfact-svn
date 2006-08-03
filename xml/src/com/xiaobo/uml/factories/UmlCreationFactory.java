package com.xiaobo.uml.factories;

import java.io.File;



import org.eclipse.gef.requests.CreationFactory;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.apache.xerces.dom.DOMImplementationImpl;
import org.apache.xerces.parsers.DOMParser;

import com.xiaobo.uml.model.Aggregation;
import com.xiaobo.uml.model.Association;
import com.xiaobo.uml.model.Attribute;
import com.xiaobo.uml.model.Compartment;
import com.xiaobo.uml.model.Inheritance;
import com.xiaobo.uml.model.Method;
import com.xiaobo.uml.model.Type;
import com.xiaobo.uml.xml.XmlCreationFactory;




/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */

public class UmlCreationFactory implements CreationFactory{

	private Class type;
	

	public UmlCreationFactory(Class type) {
		setType(type);
	}

	public Object getNewObject() {	
		if (type == Type.class) {
			System.out.println("here maybe creat a type");
			Type type = new Type();
			//XmlCreationFactory xml=new XmlCreationFactory();
			Compartment fields = new Compartment(Compartment.ATTRIBUTE_ID);
			fields.setDescription("attributes scope");
			type.addChild(fields);
			Compartment methods = new Compartment(Compartment.METHOD_ID);
			methods.setDescription("methods scope");
			type.addChild(methods);
			//XmlCreationFactory.creatType(type);
			
			return type;
		} else if (type == Attribute.class) {
			System.out.println("here maybe creat a attribut");
			return new Attribute();
		} else if (type == Method.class) {
			System.out.println("here maybe creat a method");
			return new Method();
		} else if (type == Inheritance.class) {
			System.out.println("here maybe creat an Inheritance");
			return new Inheritance();
		} else if (type == Aggregation.class) {
			return new Aggregation(true, "0..*");
		} else if (type == Association.class) {
			return new Association("", "", false, false);
		}
		return null;
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
