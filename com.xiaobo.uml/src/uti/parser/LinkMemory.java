package uti.parser;

import java.util.Vector;

import uti.codeModel.*;

import uti.java.*;

class SimpleLink {
	Link l; 
	BaseCode code;
	LinkName ln;
	Class expected;
}

class DesignatorLink {
	UtiDesignator d;
	Vector el;
}

public class LinkMemory {
	static Vector links = new Vector();
   static void add(Link l, BaseCode code, LinkName ln, Class expected)
   {
	   SimpleLink link = new SimpleLink();
	   link.l = l;
	   link.code = code;
	   link.ln = ln;
	   link.expected = expected;
	   links.addElement(link);
   }
   static void addDesignator(UtiDesignator d, Vector el)
   {
	  DesignatorLink link= new DesignatorLink();
	  link.d = d;
	  link.el = el;
	  links.addElement(el);
	  
   }
   static UtiOB searchLink(UtiOB obj, LinkName name) throws SyntaxException
   {
	   return searchLinkb(obj, name);
   }
   static UtiOB searchLinkb(UtiOB obj, LinkName name) throws SyntaxException
   {
	   if (obj == null) return null;
	   if (obj instanceof BaseCollection) {
		   BaseCollection coll = (BaseCollection)obj;
		   BaseName n = coll.getChildByName(name.getValue(0));
		   if (n != null) {
			   if (name.getCount() == 1) return (UtiOB)n;
			   //throw new SyntaxException("Unbekanntes Element: "+name.getValue(1));
			   return null;
		   } else if (obj instanceof UtiPackage) {
			  //Oh je, dass kommt später
			   return null;
		   } else {
			   return searchLink(obj.getObjParent(), name);
			   
		   }
		   
	   } else {
		   return searchLink(obj.getObjParent(), name);
		   
	   }
   }
   public static void doLink()throws SyntaxException
   {
	   for (int i = 0; i < links.size(); i++) {
		  Object o = links.elementAt(i);
		  if (o instanceof SimpleLink) {
			  SimpleLink link = (SimpleLink)o;
			  if (link.ln.getCount() == 1 && link.ln.getValue(0).equals("void"))
			  {
				  link.l.setObject(null);
				  continue;
			  }
			  UtiOB ob = searchLink((UtiOB)link.code, link.ln);
			  if (ob == null) {
				  throw new SyntaxException("Unbekannter Bezeichner: "+link.ln.toString());
				  
			  } else if (!link.expected.isInstance(ob)) {
				  throw new SyntaxException("Erwartet: "+link.expected.toString());
			  } else {
				  link.l.setObject(ob);
			  }
		  } else if (o instanceof DesignatorLink) {
			  /*DesignatorLink link = (DesignatorLink)o;
			  UtiDesignator des = link.d;
			  if (link.el.size() == 0) {
				  throw new SyntaxException("Leerer Designator");
			  }
			  Object obj = link.el.elementAt(0);
			  if (!(obj instanceof LinkName)) {
				  throw new SyntaxException("Bezeichner erwartet");
			  }
			  LinkName n = (LinkName)obj;
			  int pos=1;
			  UtiOB result=null;
			  for (int x = 1; x < n.getCount(); x++) {
				  pos = x;
				  LinkName nt = new LinkName();
				  for (int y = 0; y <= x; y++) nt.addValue(n.getValue(y), n.getToken(y));
				  result = searchLink(des, nt);
				  if (!(result instanceof UtiVariable)) result = null;
				  if (result != null) {
					  break;
				  }
				  
			  }
			  if (result == null) {
				  throw new SyntaxException("Unbekannter Bezeichner: "+n.toString());
			  }
			  UtiVariable var=(UtiVariable)result;
			  des.setBase(var);
			  UtiType currenttype = var.getDescription().getType();
			  int currentarraysize = var.getDescription().getArraySize();
			  while (pos != n.getCount()-1) {
				  if (currentarraysize != 0) {
					  throw new SyntaxException("Bezeichner erwartet und kein Array"); 
				  };
				  if (!(currenttype instanceof UtiCollection)) {
					  throw new SyntaxException("Klasse oder Interface erwartet"); 
				  }
				  UtiCollection coll = (UtiCollection)currenttype;
				  pos++;
				  String str = n.getValue(pos);
				  BaseName bn = coll.getChildByName(str);
				  if (n == null) {
					  throw new SyntaxException("Unbekannter Bezeichner"+bn); 
				  }
				  if (bn instanceof )
				  
			  }
			  for (int j = 1; j < link.el.size(); j++) {
				  obj = link.el.elementAt(j);
				  
				  
			  }*/
		  }
		   
	   }   
	   
	   links.clear();
   }
}
