package com.xiaobo.uml.xml;

import java.io.*;
import org.w3c.dom.*;

public class DOMSerializer {
	private String indent;
	private String lineSeparator;
	
	public DOMSerializer(){
		indent="";
		lineSeparator="\n";
	}
	public void setLineSeparator(String lineSeparator){
		this.lineSeparator=lineSeparator;
	}
	public void serialize(Document doc,OutputStream out)throws IOException{
		Writer writer=new OutputStreamWriter(out);
		serialize(doc,writer);
	}
	public void serialize(Document doc,File file)throws IOException{
		Writer writer=new FileWriter(file);
		serialize(doc,writer);
	}
	public void serialize(Document doc,Writer writer)throws IOException{
		serializeNode(doc,writer,"");
		writer.flush();
	}
	
	public void serializeNode(Node node,Writer writer,String indentLevel)throws IOException{
		System.out.println("are you here");
		switch(node.getNodeType()){
		case Node.DOCUMENT_NODE:
			writer.write("<?xml version=\"1.0\"?>");
			writer.write(lineSeparator);
			/*NodeList nodes=node.getChildNodes();
			if(nodes!=null){
				for(int i=0;i<nodes.getLength();i++){
					serializeNode(nodes.item(i),writer,"");
				}
			}*/
			Document doc=(Document)node;
			serializeNode(doc.getDocumentElement(),writer,"");
			break;
		case Node.ELEMENT_NODE:
			String name=node.getNodeName();
			writer.write(indentLevel+"<"+name);
			NamedNodeMap attributes=node.getAttributes();
			for(int i=0;i<attributes.getLength();i++){
				Node current=attributes.item(i);
				writer.write(" "+current.getNodeName()+"=\""+current.getNodeValue()+"\"");
			}
			writer.write(">");
			
			NodeList children=node.getChildNodes();
			if(children !=null){
				if((children.item(0)!=null)&& (children.item(0).getNodeType()==Node.ELEMENT_NODE)){
					writer.write(lineSeparator);
				}
				for(int i=0;i<children.getLength();i++){
					serializeNode(children.item(i),writer,indentLevel+indent);
				}
				if((children.item(0)!=null)&& (children.item(children.getLength()-1).getNodeType()==Node.ELEMENT_NODE)){
					writer.write(indentLevel);
				}
			}
			writer.write("</"+name+">");
			writer.write(lineSeparator);
			break;
		case Node.TEXT_NODE:
			writer.write(node.getNodeValue());
			break;
		case Node.CDATA_SECTION_NODE:
			writer.write("<![CDATA["+node.getNodeValue()+"]]>");
			break;
		}
		
	}

}