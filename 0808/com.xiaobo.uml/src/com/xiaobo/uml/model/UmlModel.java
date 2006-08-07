package com.xiaobo.uml.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.util.Vector;

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

	private Vector v = new Vector();

	public UmlModel() {

	}

	public UmlModel(Element element) {
		// NodeList childNodes = element.getChildNodes();
		NodeList childNodes = element.getElementsByTagName("type");
		for (int i = 0; i < childNodes.getLength(); i++) {
			// Node childNode = childNodes.item(i);
			Element childNode = (Element) childNodes.item(i);
			/*
			 * if (childNode.getNodeType() == Node.ELEMENT_NODE &&
			 * childNode.getNodeName().equals("type")) { addChild(new
			 * Type((Element) childNode)); v.add(((Element)
			 * childNode).getAttribute("name")); System.out.println("i found
			 * type here and added."); }
			 */
			addChild(new Type(childNode));
			v.add(childNode.getAttribute("name"));
			System.out.println("i found type " + childNode.getNodeName()
					+ " here and added.");
		}

		NodeList aggregationNodes = element.getElementsByTagName("aggregation");
		for (int i = 0; i < aggregationNodes.getLength(); i++) {
			Element aggregationNode = (Element) aggregationNodes.item(i);
			String sourceTypeName = (aggregationNode)
					.getAttribute("sourceTypeName");
			String targetTypeName = (aggregationNode)
					.getAttribute("targetTypeName");
			if (v.contains(targetTypeName) && v.contains(sourceTypeName)) {
				System.out.println("i found aggregation here and added.");
				new Aggregation(aggregationNode);
			}
		}
		NodeList associationNodes = element.getElementsByTagName("association");
		for (int i = 0; i < associationNodes.getLength(); i++) {
			Element associationNode = (Element) associationNodes.item(i);
			String sourceTypeName = (associationNode)
					.getAttribute("sourceTypeName");
			String targetTypeName = (associationNode)
					.getAttribute("targetTypeName");
			if (v.contains(targetTypeName) && v.contains(sourceTypeName)) {
				System.out.println("i found association here and added.");
				new Association(associationNode);
			}
		}
		NodeList inheritanceNodes = element.getElementsByTagName("inheritance");
		for (int i = 0; i < inheritanceNodes.getLength(); i++) {
			Element inheritanceNode = (Element) inheritanceNodes.item(i);
			String sourceTypeName = (inheritanceNode)
					.getAttribute("sourceTypeName");
			String targetTypeName = (inheritanceNode)
					.getAttribute("targetTypeName");
			if (v.contains(targetTypeName) && v.contains(sourceTypeName)) {
				System.out.println("i found inheritance here and added.");
				new Association(inheritanceNode);
			}
		}

	}

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

	public Element toDom(Document document) {
		Element rootElement = document.createElement("UmlModel");
		for (Iterator i = getChildren().iterator(); i.hasNext();) {
			Type child = (Type) i.next();
			rootElement.appendChild(child.toDom(document));

			// List tmp=(child.getIns());
			Element memberElement;
			for (int j = 0; j < ((child.getIns()).size()); j++) {
				if ((child.getIns()).get(j) instanceof Aggregation) {
					memberElement = ((Aggregation) (child.getIns()).get(j))
							.toDom(document);
					rootElement.appendChild(memberElement);
				} else if ((child.getIns()).get(j) instanceof Association) {
					memberElement = ((Association) (child.getIns()).get(j))
							.toDom(document);
					rootElement.appendChild(memberElement);
				} else if ((child.getIns()).get(j) instanceof Inheritance) {
					memberElement = ((Inheritance) (child.getIns()).get(j))
							.toDom(document);
					rootElement.appendChild(memberElement);
				}
			}

			for (int j = 0; j < ((child.getOuts()).size()); j++) {
				if ((child.getOuts()).get(j) instanceof Aggregation) {
					memberElement = ((Aggregation) (child.getOuts()).get(j))
							.toDom(document);
					rootElement.appendChild(memberElement);
				} else if ((child.getOuts()).get(j) instanceof Association) {
					memberElement = ((Association) (child.getOuts()).get(j))
							.toDom(document);
					rootElement.appendChild(memberElement);
				} else if ((child.getOuts()).get(j) instanceof Inheritance) {
					memberElement = ((Inheritance) (child.getOuts()).get(j))
							.toDom(document);
					rootElement.appendChild(memberElement);
				}
			}
		}

		return rootElement;
	}
}
