package uti.codeModel;

import java.util.*;

import org.w3c.dom.Element;

import uti.java.*;

class ArrayElement {
	int type = UtiArray.FIXED;
	//int fixedsize = 0;
	UtiType hashtype= null;
	ArrayElement()
	{
		
	}
}

public class UtiArray extends UtiType {
    Vector dimensions = new Vector();
    Link basetype = new Link();
    static final int FIXED = 0;
    static final int DYNAMIC = 1;
    static final int HASHMAP = 2;
	public UtiArray(BaseCode p) {
		super(p);
		
	};
	public void addFixed()
	{
		ArrayElement e = new ArrayElement();
		e.type = FIXED;
		dimensions.addElement(e);
	};
	public void addDynamic()
	{
		ArrayElement e = new ArrayElement();
		e.type = DYNAMIC;
		dimensions.addElement(e);
	}
	public void addHashMap(UtiType t)
	{
		ArrayElement e = new ArrayElement();
		e.type = HASHMAP;
		e.hashtype = t;
		dimensions.addElement(e);
	}
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
		UtiOB.readList(xml, "dimensions", dimensions, version, this);
	}
	public void write(Element xml, int version) {
		// TODO Auto-generated method stub
		super.write(xml, version);
		UtiOB.writeObject(xml, "basetype", basetype, version);
		UtiOB.writeList(xml, "dimensions", dimensions, version, this);
	};
	

}
