package uti.codeModel;

import java.util.*;

import org.w3c.dom.Element;

import uti.java.*;

public class UtiMethod extends UtiCollection {
    boolean utiabstract = true;
    Link resulttype = new Link();
    boolean utifinal = false;
    boolean utistatic = false;
    boolean constructor = false;
    UtiBlock block = null; 
    Vector utithrows = new Vector();
    Vector parameter = new Vector();
	public void read(Element xml, int version) {
		// TODO Auto-generated method stub
		super.read(xml, version);
		setAbstract(UtiOB.readBoolean(xml, "abstract", false));
		UtiOB.readObject(xml, "resulttype", resulttype, version);
		setFinal(UtiOB.readBoolean(xml, "final", false));
		setStatic(UtiOB.readBoolean(xml, "static", false));
		setConstructor(UtiOB.readBoolean(xml, "constructor", false));
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
		UtiOB.writeBoolean(xml, "constructor", isConstructor());
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
		return resulttype;
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
		m.setType(typ);
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
	public UtiType getResultType() {
		return (UtiType)resulttype.getObject();
	}
	public void setResultType(UtiType r) {
		this.resulttype.setObject(r);
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
	public boolean isConstructor() {
		return constructor;
	}
	public void setConstructor(boolean constructor) {
		this.constructor = constructor;
	}
	public void searchImports(ImportList list){
		Object o = resulttype.getObject();
		if (o != null && o instanceof BaseType)
			list.addSecondary((BaseType)o);
		for (int i = 0; i < parameter.size(); i++) {
			((BaseCode)parameter.elementAt(i)).searchImports(list);
		}
		block.searchImports(list);
   }
	
}
