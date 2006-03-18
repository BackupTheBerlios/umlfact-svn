package uti.codeModel;

import java.util.*;

import org.w3c.dom.Element;

import uti.java.UtiOB;

public class UtiPackage extends UtiCollection {
   Vector packages = new Vector();
   HashMap packagemap = new HashMap();
   public void setName(String name)
   {
	   if (sname.equals(name)) return;
	   UtiPackage p = (UtiPackage)getObjParent();
	   if (p == null) {
	       sname = name;
	   } else {
		   p.renamePackage(this, name);		   
	   }
   }
   public String getName()
   {
	   return sname;
   }
   public UtiPackage addPackage(String name) {
	   UtiPackage p = new UtiPackage(this);
	   p.setName(name);
	   packages.addElement(p);
	   packagemap.put(name, p);
	   return p;
   }
   
   public UtiPackage getPackageByName(String name) {
	   return (UtiPackage)packagemap.get(name);
   }
   
   public void removePackage(UtiPackage pack) {
	  packages.remove(pack);
	  packagemap.remove(pack.getName());
   }
   public void renamePackage(UtiPackage pack, String newname)
   {
	   packagemap.remove(pack.getName());
	   pack.setName(newname);
	   packagemap.put(newname, pack);
   }
   public void read(Element xml, int version) {
		// TODO Auto-generated method stub
		super.read(xml, version);
		packagemap.clear();
		UtiOB.readList(xml, "packages", packages, version, this);
		for (int i = 0; i < packages.size(); i++) {
			BaseName name = (BaseName)packages.elementAt(i);
			packagemap.put(name.getName(), name);
		}
	}

	public void write(Element xml, int version) {
		// TODO Auto-generated method stub
		super.write(xml, version);
		UtiOB.writeList(xml, "packages", packages, version);
	}
	public int getPackageCount()
	{
		return packages.size();
		
	}
	public UtiPackage getPackage(int i)
	{
		return (UtiPackage)packages.elementAt(i);
	}
   
   public UtiPackage(BaseCode p) {
	super(p);
	// TODO Auto-generated constructor stub
   }
}
