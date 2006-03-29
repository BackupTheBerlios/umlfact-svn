package uti.codeModel;

import java.util.*;

import org.w3c.dom.Element;

import uti.java.*;

public class UtiMethod extends UtiCollection {
    boolean utiabstract = true;
    TypeDescription resulttype = new TypeDescription();
    boolean utifinal = false;
    boolean utistatic = false;
    UtiBlock block = null; 
    Vector utithrows = new Vector();
    Vector parameter = new Vector();
	public void read(Element xml, int version) {
		// TODO Auto-generated method stub
		super.read(xml, version);
		setAbstract(UtiOB.readBoolean(xml, "abstract"));
		UtiOB.readObject(xml, "resulttype", resulttype, version);
		setFinal(UtiOB.readBoolean(xml, "final"));
		setStatic(UtiOB.readBoolean(xml, "static"));
		block = (UtiBlock)UtiOB.readObjectMulti(xml, "block", version, this);
		UtiOB.readList(xml, "throws", utithrows, version, this);
		UtiOB.readList(xml, "parameter", parameter, version, this);
	}
	public void write(Element xml, int version) {
		// TODO Auto-generated method stub
		super.write(xml, version);
		UtiOB.writeBoolean(xml, "abstract", isAbstract());
		UtiOB.writeObject(xml, "resulttype", resulttype, version);
		UtiOB.writeBoolean(xml, "final", isFinal());
		UtiOB.writeBoolean(xml, "static", isStatic());
		UtiOB.writeObject(xml, "block", block, version);
		UtiOB.writeList(xml, "throws", utithrows, version);
		UtiOB.writeList(xml, "parameter", parameter, version);
		
	}
	public UtiMethod(BaseCode p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	public UtiVariable addParam()
	{
		UtiVariable var = new UtiVariable(this);
		parameter.addElement(var);
		return var;
	}
	public UtiInterface addInterface(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public UtiClass addObject(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	public void addException(UtiClass obj)
	{
		utithrows.addElement(new Link(obj));
	}
	public Link intern_throw()
	{
	   Link l = new Link();
	   utithrows.addElement(l);
	   return l;
	}
	public Link intern_result()
	{
		return resulttype.type;
	}
	public int getExceptionCount()
	{
		return utithrows.size();
	}
	public UtiClass getException(int i)
	{
		return (UtiClass)((Link)utithrows.elementAt(i)).getObject();
	}
	public int getParamCount()
	{
		return children.size();
	}
	public UtiVariable getParam(int i)
	{
		return (UtiVariable)children.elementAt(i);
	}
	public UtiVariable addParam(String Name, UtiType typ)
	{
		UtiVariable m = new UtiVariable(this);
		m.setName(Name);
		m.getDescription().setType(typ);
		addChild(m);
		return m;
	}
	public UtiBlock assignBlock()
	{
		if (block != null) return block;
		block = new UtiBlock(this);
		return block;
	}
	public UtiBlock getBlock() {
		return block;
	}
	public void setBlock(UtiBlock block) {
		block.setObjParent(this);
		this.block = block;
	}
	public TypeDescription getResultType() {
		return resulttype;
	}
	public void setResultType(TypeDescription r) {
		this.resulttype = r;
	}
	public boolean isAbstract() {
		return utiabstract;
	}
	public void setAbstract(boolean utiabstract) {
		this.utiabstract = utiabstract;
	}
	public boolean isFinal() {
		return utifinal;
	}
	public void setFinal(boolean utifinal) {
		this.utifinal = utifinal;
	}
	public boolean isStatic() {
		return utistatic;
	}
	public void setStatic(boolean utistatic) {
		this.utistatic = utistatic;
	}

	public void addChild(BaseName obj) {
		// TODO Auto-generated method stub
		((UtiOB)obj).setObjParent(this);
		if (obj instanceof UtiVariable)
		super.addChild(obj);
	}
	
}
