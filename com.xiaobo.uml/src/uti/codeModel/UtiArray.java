package uti.codeModel;

import java.util.*;

import org.w3c.dom.Element;

import uti.java.*;



public class UtiArray extends UtiType implements BaseCollection {
    Link basetype = new Link();
    UtiVariable l;
	public UtiArray(BaseCode p) {
		super(p);
		l = new UtiVariable(this);
		l.setName("length");
		l.setType(BaseType.bt_int);
		
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
	public void addChild(BaseName obj) {
		// TODO Auto-generated method stub
		
	}
	public BaseName getChild(int i) {
		// TODO Auto-generated method stub
		return l;
	}
	public BaseName getChildByName(String name) {
		if (name.equals("length")) {
			return l;
		}
		return null;
	}
	public int getChildCount() {
		// TODO Auto-generated method stub
		return 1;
	}
	public void removeChild(BaseName pack) {
		// TODO Auto-generated method stub
		
	}
	public void renameChild(BaseName pack, String newname) {
		// TODO Auto-generated method stub
		
	}
	

}
