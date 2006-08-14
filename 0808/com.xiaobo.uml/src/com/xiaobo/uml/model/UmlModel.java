package com.xiaobo.uml.model;
import java.util.HashMap;
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


	public UmlModel() {

	}

	public UmlModel(Element element) {
		HashMap m = new HashMap();
		// NodeList childNodes = element.getChildNodes();
		NodeList childNodes = element.getElementsByTagName("type");
		for (int i = 0; i < childNodes.getLength(); i++) {
			// Node childNode = childNodes.item(i);
			Element childNode = (Element) childNodes.item(i);
			Type tmpType=new Type(childNode);
			addChild(tmpType);
			m.put(tmpType.getName(),tmpType);
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
			Type sourceType=(Type)m.get(sourceTypeName);
			Type targetType=(Type)m.get(targetTypeName);
			for(int j=0;j<types.size();j++){
				Type tmpType=((Type)types.get(j));
				if(tmpType.getName().equals(targetTypeName)){
					tmpType.addIn(new Aggregation(aggregationNode,sourceType,targetType));
				}
			}
		}
		NodeList associationNodes = element.getElementsByTagName("association");
		for (int i = 0; i < associationNodes.getLength(); i++) {
			Element associationNode = (Element) associationNodes.item(i);
			String sourceTypeName = (associationNode)
					.getAttribute("sourceTypeName");
			String targetTypeName = (associationNode)
					.getAttribute("targetTypeName");
			Type sourceType=(Type)m.get(sourceTypeName);
			Type targetType=(Type)m.get(targetTypeName);
			for(int j=0;j<types.size();j++){
				Type tmpType=((Type)types.get(j));
				if(tmpType.getName().equals(targetTypeName)){
					tmpType.addIn(new Association(associationNode,sourceType,targetType));
				}
			}
		}
		NodeList inheritanceNodes = element.getElementsByTagName("inheritance");
		for (int i = 0; i < inheritanceNodes.getLength(); i++) {
			Element inheritanceNode = (Element) inheritanceNodes.item(i);
			String sourceTypeName = (inheritanceNode)
					.getAttribute("sourceTypeName");
			String targetTypeName = (inheritanceNode)
					.getAttribute("targetTypeName");
			Type sourceType=(Type)m.get(sourceTypeName);
			Type targetType=(Type)m.get(targetTypeName);
			for(int j=0;j<types.size();j++){
				Type tmpType=((Type)types.get(j));
				if(tmpType.getName().equals(targetTypeName)){
					tmpType.addIn(new Inheritance(inheritanceNode,sourceType,targetType));
				}
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
		Vector v2=new Vector();
		Element rootElement = document.createElement("UmlModel");
		for (Iterator i = getChildren().iterator(); i.hasNext();) {
			Type child = (Type) i.next();
			v2.add(child.getName());
			rootElement.appendChild(child.toDom(document));

			// List tmp=(child.getIns());

		}
		for (Iterator i = getChildren().iterator(); i.hasNext();) {
			Element memberElement;
			Type child = (Type) i.next();
			for (int j = 0; j < ((child.getIns()).size()); j++) {
				if ((child.getIns()).get(j) instanceof Aggregation) {
					Aggregation agg = ((Aggregation) (child.getIns()).get(j));
					//if (!isConnected(agg, rootElement,v2)) {
						memberElement = agg.toDom(document);
						rootElement.appendChild(memberElement);
					//}

				} else if ((child.getIns()).get(j) instanceof Association) {
					Association ass = ((Association) (child.getIns()).get(j));
					//if (!isConnected(ass, rootElement,v2)) {
						memberElement = ass.toDom(document);
						rootElement.appendChild(memberElement);
					//}

				} else if ((child.getIns()).get(j) instanceof Inheritance) {
					Inheritance inh = ((Inheritance) (child.getIns()).get(j));
					//if (!isConnected(inh, rootElement,v2)) {
						memberElement = inh.toDom(document);
						rootElement.appendChild(memberElement);
					//}

				}
			}
/*
			for (int j = 0; j < ((child.getOuts()).size()); j++) {
				if ((child.getOuts()).get(j) instanceof Aggregation) {
					Aggregation agg = ((Aggregation) (child.getOuts()).get(j));
					if (!isConnected(agg, rootElement,v2)) {
						memberElement = agg.toDom(document);
						System.out.println("out agg called");
						rootElement.appendChild(memberElement);
					}
				} else if ((child.getOuts()).get(j) instanceof Association) {
					Association ass = ((Association) (child.getOuts()).get(j));
					if (!isConnected(ass, rootElement,v2)) {
						memberElement = ass.toDom(document);
						System.out.println("out ass called");
						rootElement.appendChild(memberElement);
					}
				} else if ((child.getOuts()).get(j) instanceof Inheritance) {
					Inheritance inh = ((Inheritance) (child.getOuts()).get(j));
					if (!isConnected(inh, rootElement,v2)) {
						memberElement = inh.toDom(document);
						System.out.println("out inh called");
						rootElement.appendChild(memberElement);
					}
				}
			}*/
		}

		return rootElement;
	}
/*
	public boolean isConnected(ConnectionModel cm, Element element,Vector v2) {
		if (cm instanceof Aggregation) {
			NodeList aggregationNodes = element
					.getElementsByTagName("aggregation");
			for (int i = 0; i < aggregationNodes.getLength(); i++) {
				Element aggregationNode = (Element) aggregationNodes.item(i);
				String sourceTypeName = (aggregationNode)
						.getAttribute("sourceTypeName");
				String targetTypeName = (aggregationNode)
						.getAttribute("targetTypeName");
				if (v2.contains(targetTypeName) && v2.contains(sourceTypeName)) {
					return true;
				}
			}
		} else if (cm instanceof Association) {
			NodeList associationNodes = element
					.getElementsByTagName("association");
			for (int i = 0; i < associationNodes.getLength(); i++) {
				Element associationNode = (Element) associationNodes.item(i);
				String sourceTypeName = (associationNode)
						.getAttribute("sourceTypeName");
				String targetTypeName = (associationNode)
						.getAttribute("targetTypeName");
				if (v2.contains(targetTypeName) && v2.contains(sourceTypeName)) {
					return true;
				}
			}

		} else if (cm instanceof Inheritance) {
			NodeList inheritanceNodes = element
					.getElementsByTagName("inheritance");
			for (int i = 0; i < inheritanceNodes.getLength(); i++) {
				Element inheritanceNode = (Element) inheritanceNodes.item(i);
				String sourceTypeName = (inheritanceNode)
						.getAttribute("sourceTypeName");
				String targetTypeName = (inheritanceNode)
						.getAttribute("targetTypeName");
				if (v2.contains(targetTypeName) && v2.contains(sourceTypeName)) {
					return true;
				}
			}

		}
		return false;

	}*/
}
