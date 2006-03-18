package uti.codeModel;

import java.util.Vector;

import org.w3c.dom.Element;
import uti.java.*;
import uti.java.Link;

public class UtiClass extends UtiCollection {
	boolean utiabstract;
	Link extendsobj = new Link();
	Vector implementsobj = new Vector();
	public UtiClass(BaseCode p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	public void setExtends(UtiClass cl)
	{
		extendsobj.setObject(cl);
	}
	public UtiClass getExtends()
	{
		return (UtiClass)extendsobj.getObject();
	}
	public void addImplement(UtiInterface i)
	{
		implementsobj.addElement(new Link(i));
	}
	
	
	public void read(Element xml, int version) {
		// TODO Auto-generated method stub
		super.read(xml, version);
		UtiOB.readObject(xml, "extends", extendsobj, version);
		UtiOB.readList(xml, "implements", implementsobj, version, this);
		setAbstract(UtiOB.readBoolean(xml, "abstract"));
	}
	public void write(Element xml, int version) {
		// TODO Auto-generated method stub
		super.write(xml, version);
		UtiOB.writeObject(xml, "extends", extendsobj, version);
		UtiOB.writeList(xml, "implements", implementsobj, version);
		UtiOB.writeBoolean(xml, "abstract", isAbstract());
	}
	public UtiMethod addMethod(String Name)
	{
		UtiMethod m = new UtiMethod(this);
		m.setName(Name);
		addChild(m);
		return m;
	}
	public UtiVariable addVariable(String Name, UtiType typ)
	{
		UtiVariable m = new UtiVariable(this);
		m.setName(Name);
		m.setType(typ);
		addChild(m);
		return m;
	}
	public boolean isAbstract() {
		return utiabstract;
	}
	public void setAbstract(boolean utiabstract) {
		this.utiabstract = utiabstract;
	}
}
