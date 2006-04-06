package uti.codeModel;

import java.util.*;

import org.w3c.dom.Element;

import uti.java.*;
/** Veraltet nicht benutzen*/
/*public class UtiDesignator extends UtiCommand {
    Vector steps = new Vector();
    Link basevar= new Link();
	public UtiDesignator(BaseCode p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	public void addFieldAccess(UtiVariable var) {
		UtiDesField f = new UtiDesField(this, var);
		steps.addElement(f);
	}
	public void addMethodAccess(UtiMethod var, Vector parameter) {
		UtiDesMethod f = new UtiDesMethod(this, var, parameter);
		steps.addElement(f);
	}
	public void addArrayAccess(UtiAusdruck[] exp) {
		UtiDesArray f = new UtiDesArray(this, exp);
		steps.addElement(f);
	}
	public void setBase(BaseCommand v)
	{
		basevar.setObject((UtiOB)v);
	}
	public BaseCommand getBase()
	{
		return (BaseCommand)basevar.getObject();
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

}*/
