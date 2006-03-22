package uti.codeModel;

import java.util.Vector;

import org.w3c.dom.Element;
import uti.java.*;

public class UtiClass extends UtiCollection {
	boolean utiabstract=false;
	boolean utipublic=false;;
	boolean utifinal=false;;

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
	public Link intern_extends_link()
	{
		return extendsobj;
	}
	public Link intern_impl_link()
	{
		Link l = new Link();
		implementsobj.addElement(l);
		return l;
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
		setPublic(UtiOB.readBoolean(xml, "public"));
		setFinal(UtiOB.readBoolean(xml, "final"));
	}
	public void write(Element xml, int version) {
		// TODO Auto-generated method stub
		super.write(xml, version);
		UtiOB.writeObject(xml, "extends", extendsobj, version);
		UtiOB.writeList(xml, "implements", implementsobj, version);
		UtiOB.writeBoolean(xml, "abstract", isAbstract());
		UtiOB.writeBoolean(xml, "public", isPublic());
		UtiOB.writeBoolean(xml, "final", isFinal());
	}
	public UtiMethod addMethod(String Name)
	{
		UtiMethod m = new UtiMethod(this);
		m.setName(Name);
		addChild(m);
		return m;
	}
	/*public UtiVariable addVariable(String Name, UtiType typ)
	{
		UtiVariable m = new UtiVariable(this);
		m.setName(Name);
		
		addChild(m);
		return m;
	}*/
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
	public boolean isPublic() {
		return utipublic;
	}
	public void setPublic(boolean utipublic) {
		this.utipublic = utipublic;
	}
	public void addChild(BaseName obj) {
		// TODO Auto-generated method stub
		super.addChild(obj);
		System.out.println("class "+getName()+" "+obj.getName());
	}
}
