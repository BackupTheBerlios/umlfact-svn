package uti.codeModel;

import java.util.HashMap;
import java.util.Vector;

import org.w3c.dom.Element;

import uti.java.UtiOB;

public class UtiFor extends UtiCommand implements BaseCollection{
	Vector children = new Vector();
    Vector init = new Vector();
    Vector update = new Vector();
	HashMap childrenmap = new HashMap();
	UtiBlock block=new UtiBlock(this);
	UtiAusdruck bedingung = new UtiAusdruck(this);
	public UtiFor(BaseCode p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	public UtiBlock getBlock() {
		return block;
	}
	public void setBlock(UtiBlock code) {
		code.setObjParent(this);
		this.block = code;
	}
	public void addInit(BaseCode code) {
		code.setObjParent(this);
		init.addElement(code);
		if (code instanceof BaseName) {
			addChild((BaseName)code);
		}
	}
	public int getInitCount()
	{
		return init.size();
	}
	public BaseCode getInit(int i)
	{
		return (BaseCode)init.elementAt(i);
	}
	public void addUpdate(UtiAusdruck code) {
		code.setObjParent(this);
		update.addElement(code);
		
	}
	public int getUpdateCount()
	{
		return update.size();
	}
	public UtiAusdruck getUpdate(int i)
	{
		return (UtiAusdruck)update.elementAt(i);
	}
	public UtiAusdruck getBedingung() {
		return bedingung;
	}
	public void setBedingung(UtiAusdruck bedingung) {
		bedingung.setObjParent(this);
		this.bedingung = bedingung;
	}
	
	public void read(Element xml, int version) {
		
		super.read(xml, version);
		UtiOB.readObject(xml, "block", block, version);
		
		UtiOB.readObject(xml, "bedingung", bedingung, version);
		childrenmap.clear();
		children.clear();
		UtiOB.readList(xml, "init", init, version, this);
		UtiOB.readList(xml, "update", update, version, this);
		for (int i = 0; i < init.size(); i++) {
		   if (init.elementAt(i) instanceof BaseName) {
			  BaseName name = (BaseName)init.elementAt(i);
			 addChild(name);
		   }
		}
	}

	public void write(Element xml, int version) {
		
		super.write(xml, version);
		UtiOB.writeObject(xml, "block", block, version);
		UtiOB.writeObject(xml, "bedingung", bedingung, version);
		UtiOB.writeList(xml, "init", init, version);
		UtiOB.writeList(xml, "update", update, version);
	}
	public void addChild(BaseName obj) {

		children.addElement(obj);
		childrenmap.put(obj.getName(), obj);
		((UtiOB)obj).setObjParent(this);

	}

	public UtiInterface addInterface(String name) {

		return null;
	}

	public UtiClass addObject(String name) {

		return null;
	}

	public BaseName getChildByName(String name) {

		return (BaseName) childrenmap.get(name);
	}

	public void removeChild(BaseName pack) {

		children.remove(pack);
		childrenmap.remove(pack.getName());
		
	}
	public int getChildCount() {
		return children.size();

	}
	public BaseName getChild(int i) {
		return (BaseName) children.elementAt(i);
	}
	public void renameChild(BaseName pack, String newname) {
		childrenmap.remove(pack.getName());
		pack.setName(newname);
		childrenmap.put(newname, pack);
	}
	public void searchImports(ImportList list){
		
		for (int i = 0; i < init.size(); i++) {
			((BaseCode)init.elementAt(i)).searchImports(list);
		}
		for (int i = 0; i < update.size(); i++) {
			((BaseCode)update.elementAt(i)).searchImports(list);
		}
		bedingung.searchImports(list);
		block.searchImports(list);
   }
}
