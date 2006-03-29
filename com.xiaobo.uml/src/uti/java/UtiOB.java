package uti.java;

import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

public class UtiOB {
  
   public String getPropertyName()
   {
      return toString();
   }

   public static Object CreateClass(UtiOB Parent, String id)
   {
      Object ob = null;
      try {
         Class cl = Class.forName(id); /* numberlist.get(id)*/ ;
         ob =  cl.newInstance();
         if (ob instanceof UtiOB)
            ((UtiOB)ob).Parent = Parent;
      } catch (Exception e) {
         System.out.println(e.toString());
         e.printStackTrace();
      }
      return ob;
   }

   public static HashMap loadobj = new HashMap();
   public static HashMap staticref = new HashMap();
   public static Vector loadedlinks = new Vector();
   public static void addStaticRef(UtiOB ob) {
	   staticref.put(new Long(ob.id), ob);
   }
   long id;
   public UtiOB Parent;
   public UtiOB()
   {
      this(null);
   }

   public UtiOB getObjParent()
   {
      return Parent;
   }

   public void setObjParent(UtiOB p)
   {
      Parent = p;
   }

   public void setID(long nid)
   {
      id = nid;
   }

   public long getID()
   {
      return id;
   }

   public void free()
   {

   }

   public UtiOB(UtiOB p)
   {
      Parent = p;
      id = (long) (Math.random() * 1000000 + Math.random() * 1000000000) + 1000;
   }

   public String getTypeInfo()
   {
      String k = this.getClass().getName()
          /*(String)classlist.get(this.getClass())*/;
      return k;
   }
   public static void writeString(Element xml, String name, String value)
   {
	   xml.setAttribute(name, value);
   }
   public static String readString(Element xml, String name)
   {
	   return xml.getAttribute(name);
   }
   public static void writeDouble(Element xml, String name, double d)
   {
      xml.setAttribute(name, Double.toString(d));
   }

   public static double readDouble(Element xml, String name)
   {
      return Double.parseDouble(xml.getAttribute(name));
   }

   public static void writeInteger(Element xml, String name, int d)
   {
      xml.setAttribute(name, Integer.toString(d));
   }

   public static int readInteger(Element xml, String name)
   {
      return Integer.parseInt(xml.getAttribute(name));
   }

   public static void writeBoolean(Element xml, String name, boolean d)
   {
      xml.setAttribute(name, Boolean.toString(d));
   }

   public static boolean readBoolean(Element xml, String name)
   {
      return Boolean.getBoolean(xml.getAttribute(name));
   }

   public static void writeObject(Element xml,String name, UtiOB obj, 
                                  int version)
   {
      Document d = xml.getOwnerDocument();
      Element sItem = d.createElement(name);
      xml.appendChild(sItem);
      obj.write(sItem, version);
   }

   public static void readObject(Element xml,String name, UtiOB obj,  
                                 int version)
   {
      Node k = xml.getFirstChild();
      while (k != null) {
         if (k.getNodeType() == Node.ELEMENT_NODE) {
            Element el = (Element) k;
            if (el.getNodeName().equals( name)) {
               obj.read(el, version);
               return;
            }
         }
         k = k.getNextSibling();
      }
   }

   public static UtiOB readObjectMulti(Element xml, String name, int version, UtiOB Parent)
   {
      Node k = xml.getFirstChild();
      while (k != null) {
         if (k.getNodeType() == Node.ELEMENT_NODE) {
            Element el = (Element) k;
            if (el.getNodeName().equals(name)) {
               UtiOB obj = (UtiOB)UtiOB.CreateClass(Parent, el.getAttribute("_type"));
               obj.read(el, version);
               return obj;
            }
         }
         k = k.getNextSibling();
      }
      return null;
   }

   public static void writeList(Element xml,String name, Vector Items,
                                int version)
   {
      Document d = xml.getOwnerDocument();
      Element sItem = d.createElement(name);
      xml.appendChild(sItem);
      for (int i = 0; i < Items.size(); i++) {
    	  Object obj = Items.elementAt(i);
    	  if (obj instanceof UtiOB) {
            UtiOB ob = (UtiOB) obj;
            Element sub = d.createElement("class");
            sItem.appendChild(sub);
            ob.write(sub, version);
    	  }
      }
      ;

   }

   public static void readList(Element xml, String name,Vector Items,
                               int version, UtiOB Parent)
   {
      Items.removeAllElements();
      Node k = xml.getFirstChild();
      while (k != null) {
         if (k.getNodeType() == Node.ELEMENT_NODE) {
            Element el = (Element) k;
            if (el.getNodeName().equals(name)) {
               Node n1 = el.getFirstChild();
               while (n1 != null) {
                  if (n1.getNodeType() == Node.ELEMENT_NODE) {
                     Element e = (Element) n1;
                     String l = e.getAttribute("_type");
                     Object obj = UtiOB.CreateClass(Parent, l);
                     UtiOB no = (UtiOB)obj;
                     no.read(e, version);
                     Items.addElement(no);
                  }
                  n1 = n1.getNextSibling();
               }
               return;
            }
         }
         k = k.getNextSibling();
      }
   }

   public void write(Element xml, int version)
   {
      // xml.set�
      Document d = xml.getOwnerDocument();
      //Attr a = d.createAttribute("id");
      //a.setValue(Long.toHexString(id));
      xml.setAttribute("_id", Long.toHexString(id));
      xml.setAttribute("_type", getTypeInfo());

   }

   public void read(Element xml, int version)
   {
      String d = xml.getAttribute("_id");
      setID(Long.parseLong(d, 16));
      loadobj.put(new Long(id), this);
   }

   public void saveToFile(String name)
   {
      try {
         DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
         DocumentBuilder b = f.newDocumentBuilder();
         Document d = b.newDocument();
         Element root = d.createElement("root");
         d.appendChild(root);
         write(root, 1);

         TransformerFactory fac = TransformerFactory.newInstance();
         Transformer trans = fac.newTransformer( /*new DOMSource(d)*/);
         trans.transform(new DOMSource(d), new StreamResult(name));
      } catch (Exception e) {
         e.printStackTrace();

      }
   }

   public void loadFromFile(String name)
   {
      try {
         DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
         DocumentBuilder b = f.newDocumentBuilder();
         Document d = b.parse(name);
         Node root = d.getFirstChild();
         loadedlinks.clear();
         loadobj.clear();
         if (root.getNodeType() == Node.ELEMENT_NODE) {

            read( (Element) root, 1);
         }
         ;
         for (int i = 0; i < loadedlinks.size(); i++) {
            Link l = (Link) loadedlinks.elementAt(i);
            l.dolink();
         }
         loadedlinks.clear();
         loadobj.clear();
      } catch (Exception e) {
         e.printStackTrace();

      }
   }

}
