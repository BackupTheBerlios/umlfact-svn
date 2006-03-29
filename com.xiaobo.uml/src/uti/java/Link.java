package uti.java;

import org.w3c.dom.*;

public class Link extends UtiOB {
   UtiOB Obj = null;
   long ref = 0;
   public UtiOB getObject()
   {
      return Obj;
   }

   public void setObject(UtiOB ob)
   {
      Obj = ob;
      if (ob != null) {
         ref = ob.id;
      } else {
         ref = 0;
      }
   }

   public Link()
   {
      Obj = null;
   }

   public Link(UtiOB ref)
   {
      setObject(ref);
   }

   public void dolink()
   {
      Long l = new Long(ref);
      Object o = UtiOB.loadobj.get(l);
      if (o == null) {
    	  o = UtiOB.staticref.get(l);
      }
      Obj = (UtiOB) o;
   }

  /* public void write(Element node, String name)
   {
      node.setAttribute(name, Long.toHexString(ref));
   }

   public void read(Element node, String name)
   {
      String k = node.getAttribute(name);
      ref = Long.parseLong(k, 16);
      Object = null;
      UtiOB.loadedlinks.addElement(this);
   }*/
   public void write(Element xml, int version)
   {
	   super.write(xml, version);
	   xml.setAttribute("link", Long.toHexString(ref));
   }
   public void read(Element xml, int version)
   {
	   //super.write(xml, version);
	   String k = xml.getAttribute("link");
	      ref = Long.parseLong(k, 16);
	      Obj = null;
	      UtiOB.loadedlinks.addElement(this);
	 
   }
}