package uti.java;

import org.w3c.dom.*;

/**
 * Klasse, die eine Verknüpfung realisiert. Diese kann nach dem neuladen 
 * wiederhergestellt werden.
 * @author staud
 *
 */

public class Link extends UtiOB {
   UtiOB Obj = null;
   long ref = 0;
   /**
    * Gibt das Objekt zurück, auf das die Verknüpfung zeigt.
    * @return Das verknüpfte Objekt.
    */
   public UtiOB getObject()
   {
      return Obj;
   }
   /**
    * Setzt das Objekt auf das die Verknüpfung zeigt.
    * @param ob
    */
   public void setObject(UtiOB ob)
   {
      Obj = ob;
      if (ob != null) {
         ref = ob.id;
      } else {
         ref = 0;
      }
   }
   /**
    * Erzeugt eine neue Verknüpfung die auf nichts zeigt.
    *
    */
   public Link()
   {
      Obj = null;
   }
   /**
    * Erzeugt eine neue Verknüpfung die auf ref zeigt.
    * @param ref Das Objekt auf das die Verknüpfung zeigen soll.
    */
   public Link(UtiOB ref)
   {
      setObject(ref);
   }
   /**
    * Diese Methode wird von UtiOB.loadfromfile aufgerufen um die Verknüpfung 
    * nach dem neuladen wiederherzustellen.
    *
    */
   public void dolink()
   {
      Long l = new Long(ref);
      Object o = UtiOB.loadobj.get(l);
      if (o == null) {
    	  o = UtiOB.staticref.get(l);
      }
      Obj = (UtiOB) o;
   }

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