package uti.codeModel;

import java.util.*;

public class UtiMethod extends UtiCollection {
    boolean utiabstract = true;
    UtiType resulttype = null;
    boolean utifinal = false;
    boolean utistatic = false;
    UtiBlock block = null; 
    Vector utithrows = new Vector();
    Vector parameter = new Vector();
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
		utithrows.addElement(obj);
	}
	public int getExceptionCount()
	{
		return utithrows.size();
	}
	public UtiClass getException(int i)
	{
		return (UtiClass)utithrows.elementAt(i);
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
		this.block = block;
	}
	public UtiType getResultType() {
		return resulttype;
	}
	public void setResultType(UtiType resulttype) {
		this.resulttype = resulttype;
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
		if (obj instanceof UtiVariable)
		super.addChild(obj);
	}
	
}
