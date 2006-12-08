/**
 * 
 * @author xiaobo. Created on Oct 15, 2006.
 * 
 * Copyright 2006 by Xiaobo Sun. All rights reserved.
 *
 */
package com.xiaobo.uml;

import java.io.File;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.xiaobo.uml.model.Aggregation;
import com.xiaobo.uml.model.Attribute;
import com.xiaobo.uml.model.Compartment;
import com.xiaobo.uml.model.IUmlConnection;
import com.xiaobo.uml.model.Inheritance;
import com.xiaobo.uml.model.Member;
import com.xiaobo.uml.model.Type;
import com.xiaobo.uml.model.UmlModel;

public class XSDWriter {

	private static XSDWriter instance;

	private Document doc;

	public XSDWriter() {
	}

	public static XSDWriter getInstance() {
		if (instance == null) {
			instance = new XSDWriter();
		}
		return instance;
	}

	public void write(UmlModel model, String path) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();
			doc = builder.newDocument();
			doc.appendChild(rootToDom(model));
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(path));
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Node rootToDom(UmlModel root) {
		Element result = doc.createElement("xsd:schema");
		result.setAttribute("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
		result.setAttribute("xmlns:umlf", "http://umlfact.berlios.de/umlf");
		// in which scope(namespace) the specifications defined here are valid
		result
				.setAttribute("targetNamespace",
						"http://umlfact.berlios.de/umlf");
		// unqualified is default, means the local elements and attributes
		// defined in this xsd are located in the anonym namespace, else located
		// in the targetNamespace (have to be explicitly declared in the xml
		// instance of this xsd).
		result.setAttribute("elementFormDefault", "unqualified");
		result.setAttribute("attributeFormDefault", "unqualified");

		for (Iterator<Type> i = root.getChildren().iterator(); i.hasNext();) {
			Type type = i.next();
			createContainer(doc, result, type);

			Element typeElem = doc.createElement("xsd:element");
			result.appendChild(typeElem);
			typeElem.setAttribute("name", type.getName());
			Element complexType = doc.createElement("xsd:complexType");
			typeElem.appendChild(complexType);
			Element sequence = doc.createElement("xsd:sequence");
			complexType.appendChild(sequence);
			
			createAggregation(doc, sequence, type);
			
			Element attributeGroupRef = doc.createElement("xsd:attributeGroup");
			complexType.appendChild(attributeGroupRef);
			attributeGroupRef.setAttribute("ref", "umlf:" + type.getName()
					+ "Attributes");
			createAttributes(doc, result, type);
			
			createInheritance(doc, complexType, type);
		}
		return result;
	}

	private void createContainer(Document doc2, Element result, Type type) {
		Element types = doc.createElement("xsd:element");
		result.appendChild(types);
		types.setAttribute("name", type.getName() + "s");
		Element complexType = doc.createElement("xsd:complexType");
		types.appendChild(complexType);
		Element sequence = doc.createElement("xsd:sequence");
		complexType.appendChild(sequence);
		Element typeRef = doc.createElement("xsd:element");
		sequence.appendChild(typeRef);
		typeRef.setAttribute("ref", "umlf:" + type.getName());
		typeRef.setAttribute("minOccurs", "1");
		typeRef.setAttribute("maxOccurs", "unbounded");
	}

	private void createAggregation(Document doc, Element sequence, Type type) {
		for (Iterator<IUmlConnection> i = type.getIns().iterator(); i.hasNext();) {
			IUmlConnection connection = i.next();
			if (connection instanceof Aggregation) {
				Aggregation aggregation = (Aggregation) connection;
				Type source = (Type) aggregation.getSource();
				Element subElementRef = doc.createElement("xsd:element");
				sequence.appendChild(subElementRef);
				subElementRef.setAttribute("ref", "umlf:" + source.getName());
				subElementRef.setAttribute("minOccurs", "0");
				subElementRef.setAttribute("maxOccurs", "unbounded");
			}
		}
	}

	private void createAttributes(Document doc, Element result, Type type) {
		Element attributeGroup = doc.createElement("xsd:attributeGroup");
		result.appendChild(attributeGroup);
		attributeGroup.setAttribute("name", type.getName() + "Attributes");
		Compartment attributes = type.getChildren().get(0);
		for (Iterator<Member> i = attributes.getChildren().iterator(); i
				.hasNext();) {
			Element attributeElem = doc.createElement("xsd:attribute");
			attributeGroup.appendChild(attributeElem);
			Attribute attribute = (Attribute) i.next();
			String nameString = attribute.getName();
			int index = nameString.indexOf(":");
			String name = nameString.substring(1, index);
			attributeElem.setAttribute("name", name);
			String typeString = nameString.substring(index + 1);
			if (typeString.equalsIgnoreCase("string")) {
				attributeElem.setAttribute("type", "xsd:string");
			} else if (typeString.equalsIgnoreCase("int")) {
				attributeElem.setAttribute("type", "xsd:integer");
			} else if (typeString.equalsIgnoreCase("double")) {
				attributeElem.setAttribute("type", "xsd:double");
			} else if (typeString.equalsIgnoreCase("float")) {
				attributeElem.setAttribute("type", "xsd:float");
			} else if (typeString.equalsIgnoreCase("boolean")) {
				attributeElem.setAttribute("type", "xsd:boolean");
			} else {
				attributeElem.setAttribute("type", typeString);
			}
			attributeElem.setAttribute("use", "required");
		}
	}

	private void createInheritance(Document doc, Element complexType, Type type) {
		for (Iterator<IUmlConnection> i = type.getOuts().iterator(); i
				.hasNext();) {
			IUmlConnection connection = i.next();
			if (connection instanceof Inheritance) {
				Inheritance inheritance = (Inheritance) connection;
				Type target = (Type) inheritance.getTarget();
				Element attributeGroupRef = doc
						.createElement("xsd:attributeGroup");
				complexType.appendChild(attributeGroupRef);
				attributeGroupRef.setAttribute("ref", "umlf:"
						+ target.getName() + "Attributes");
			}
		}
	}
}
