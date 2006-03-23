package uti.codeModel;

import java.util.*;

import org.w3c.dom.Element;

import uti.java.*;

public class UtiDesignator extends UtiCommand {
    Vector steps = new Vector();
    Link basevar= new Link();
	public UtiDesignator(BaseCode p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	void addFieldAccess(UtiVariable var) {
		UtiDesField f = new UtiDesField(this, var);
		steps.addElement(f);
	}
	void addMethodAccess(UtiMethod var, Vector parameter) {
		UtiDesMethod f = new UtiDesMethod(this, var, parameter);
		steps.addElement(f);
	}
	void addArrayAccess(UtiAusdruck[] exp) {
		UtiDesArray f = new UtiDesArray(this, exp);
		steps.addElement(f);
	}
	public void setBase(UtiVariable v)
	{
		basevar.setObject(v);
	}
	public UtiVariable getBase()
	{
		return (UtiVariable)basevar.getObject();
	}
	public void read(Element xml, int version) {
		// TODO Auto-generated method stub
		super.read(xml, version);
		UtiOB.readList(xml, "steps", steps, version, this);
		UtiOB.readObject(xml, "base", basevar, version);
	}
	public void write(Element xml, int version) {
		// TODO Auto-generated method stub
		super.write(xml, version);
		UtiOB.writeList(xml, "steps", steps, version);
		UtiOB.writeObject(xml, "base", basevar, version);
	}

}
