package uti.codeModel;

import org.w3c.dom.Element;

import uti.java.*;

/**
 * Basisklasse aller Codeobjekte.
 * @author staud
 *
 */

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
	/**
	 * Diese Methode sucht nach allen Typen die beim Export importiert werden müssen
	 * @param list Liste der verwendeten externen Objekte.
	 */
	public void searchImports(ImportList list){
		
	}
}
