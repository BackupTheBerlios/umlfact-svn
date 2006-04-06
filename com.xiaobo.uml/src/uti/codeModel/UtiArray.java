package uti.codeModel;

import java.util.*;

import org.w3c.dom.Element;

import uti.java.*;



public class UtiArray extends UtiType {
    Link basetype = new Link();
    static final int FIXED = 0;
    static final int DYNAMIC = 1;
    static final int HASHMAP = 2;
	public UtiArray(BaseCode p) {
		super(p);
		
	};
	public UtiType getBasetype() {
		return (UtiType)basetype.getObject();
	}
	public void setBasetype(UtiType basetype) {
		this.basetype.setObject(basetype);
	}
	public void read(Element xml, int version) {
		// TODO Auto-generated method stub
		super.read(xml, version);
		UtiOB.readObject(xml, "basetype", basetype, version);
	}
	public void write(Element xml, int version) {
		// TODO Auto-generated method stub
		super.write(xml, version);
		UtiOB.writeObject(xml, "basetype", basetype, version);
	};
	public void searchImports(ImportList list){
		Object o = basetype.getObject();
	
		if (o instanceof BaseType)
			list.addSecondary((BaseType)o);
		((BaseCode)o).searchImports(list);
		list.addSecondary(this);
	}
	

}
