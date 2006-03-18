package uti.codeModel;

import org.w3c.dom.Element;

import uti.java.*;

public class BaseCode extends UtiOB {
	 //  Link Parent=new Link();
	   //String sname = "";
	  
	public BaseCode() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BaseCode(UtiOB p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	public void read(Element xml, int version) {
		
		super.read(xml, version);
	
	}
	public void write(Element xml, int version) {
		// TODO Auto-generated method stub
		super.write(xml, version);
	}
}
