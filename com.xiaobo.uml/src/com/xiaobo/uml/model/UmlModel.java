package com.xiaobo.uml.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlModel extends UmlElement implements IUmlContainer {

	private static final long serialVersionUID = 1L;

	private static final String CHILD_PROP = "child";

	private List<Type> types = new ArrayList<Type>();

	public UmlModel() {

	}

	public UmlModel(Element element) {
		NodeList childNodes = element.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node childNode = childNodes.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE
					&& childNode.getNodeName().equals("type")) {
				addChild(new Type((Element) childNode));
			}
		}
	}

	public List<Type> getChildren() {
		return types;
	}

	public void addChild(IUmlElement element) {
		if (!(element instanceof Type)) {
			throw new IllegalArgumentException();
		}
		types.add((Type) element);
		firePropertyChange(CHILD_PROP);
	}

	public void addChild(IUmlElement element, int index) {
		if (!(element instanceof Type)) {
			throw new IllegalArgumentException();
		}
		types.add(index, (Type) element);
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
		Element rootElement = document.createElement("Uml Model");
		for (Iterator i = getChildren().iterator(); i.hasNext();) {
			Type child = (Type) i.next();
			rootElement.appendChild(child.toDom(document));
		}
		return rootElement;
	}
}
