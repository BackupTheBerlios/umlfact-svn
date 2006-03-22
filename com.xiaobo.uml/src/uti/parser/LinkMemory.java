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
			   throw new SyntaxException("Unbekanntes Element: "+name.getValue(1));
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
			  DesignatorLink link = (DesignatorLink)o;
			  
		  }
		   
	   }   
	   
	   links.clear();
   }
}
