package uti.codeModel;

import java.util.HashMap;
import java.util.Vector;

import org.w3c.dom.Element;
import uti.java.*;


public class UtiCollection extends UtiType implements BaseCollection {
	Vector children = new Vector();
	HashMap childrenmap = new HashMap();
	public UtiCollection(BaseCode p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	public void removeChild(BaseName pack) {
		   children.remove(pack);
		   childrenmap.remove(pack.getName());
	}
	
	public void renameChild(BaseName pack, String newname)
	   {
		   if (!childrenmap.containsKey(pack.getName())) return;
		   childrenmap.remove(pack.getName());
		   pack.setName(newname);
		   childrenmap.put(newname, pack);
	   }
	 
	public BaseName getChildByName(String name) {
		   return (BaseName)childrenmap.get(name);
	   }
	
	public void addChild(BaseName obj)
	   {
		   children.addElement(obj);
		   childrenmap.put(obj.getName(), obj);
		   ((UtiOB)obj).setObjParent(this);
	   }
	public int getChildCount()
	{
		return children.size();
		
	}
	public BaseName getChild(int i)
	{
		return (BaseName)children.elementAt(i);
	}
	  
	public UtiClass addObject(String name) {
		   UtiClass obj = new UtiClass(this);
		   obj.setName(name);
		   addChild(obj);
		   return obj;
	   }
	  
	public UtiInterface addInterface(String name) {
		   UtiInterface obj = new UtiInterface(this);
		   obj.setName(name);
		   addChild(obj);
		   return obj;
	   }

	public void read(Element xml, int version) {
		// TODO Auto-generated method stub
		super.read(xml, version);
		childrenmap.clear();
		UtiOB.readList(xml, "children", children, version, this);
		for (int i = 0; i < children.size(); i++) {
			BaseName name = (BaseName)children.elementAt(i);
			childrenmap.put(name.getName(), name);
		}
	}

	public void write(Element xml, int version) {
		// TODO Auto-generated method stub
		super.write(xml, version);
		UtiOB.writeList(xml, "children", children, version);
	}
	public void searchImports(ImportList list){
	    for (int i = 0; i < children.size(); i++) {
	    	((BaseCode)children.elementAt(i)).searchImports(list);
	    }
	}
	   
}
