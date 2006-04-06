package uti.java;

import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;


/**
 * Basisklasse aller Objekt in diesem System. Hier werden grundlegende 
 * Verwaltungsfunktion implementiert, wie das abspeichern.
 * @author staud
 *
 */
public class UtiOB {
   /**
    * Erzeugt mit Hilfe des Klassennamens ein Objekt
    * @param Parent Das übergeordnete Objekt des neuen Objekts
    * @param id Der Klassenname
    * @return Ein neues Objekt das vom Typ Klassenname ist.
    */
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
   /**
    * Gibt das übergeordnete Objekt zurück
    * @return Das übergeordnete Objekt
    */
   public UtiOB getObjParent()
   {
      return Parent;
   }
   /**
    * Setzt das übergeordnete Objekt
    * @param p
    */
   public void setObjParent(UtiOB p)
   {
      Parent = p;
   }
   /**
    * Setzt die ID des Objekts. Diese ID sollte dann kein anderes Objekt haben.
    * Sonst wird das ganze unangenehm
    * @param nid Die neue ID
    */
   public void setID(long nid)
   {
      id = nid;
   }
   /**
    * Gibt die ID des Objekts zurück. Diese bleibt nach dem neuladen gleich.
    * Mit dieser ID werden Verknüpfungen nach dem neuladen wieder hergestellt 
    * @return Die ID des Objekts
    */
   public long getID()
   {
      return id;
   }

   public void free()
   {

   }
   /**
    * Erzeugt ein neues UtiOB Objekt mit einer neuen eindeutigen ID.
    * @param p Das übergeordnete Objekt
    */
   public UtiOB(UtiOB p)
   {
      Parent = p;
      id = (long) (Math.random() * 1000000 + Math.random() * 1000000000) + 1000;
   }
   /**
    * Gibt den Klassenname des Objekts zurück
    * @return KlassenName
    */
   public String getTypeInfo()
   {
      String k = this.getClass().getName()
          /*(String)classlist.get(this.getClass())*/;
      return k;
   }
   /**
    * Speichert einen String Wert in einem XML Knoten
    * @param xml XML Knoten
    * @param name Name des Attributs
    * @param value Der zu speichernde Wert
    */
   public static void writeString(Element xml, String name, String value)
   {
	   xml.setAttribute(name, value);
   }
   /**
    * List einen String Wert aus einem XML Knoten
    * @param xml XML Knoten 
    * @param name Name des Attributs
    * @param def Der Defaultwert, falls das Attribut nicht existiert
    * @return Der gelesen Wert.
    */
   public static String readString(Element xml, String name, String def)
   {
	   if (xml.hasAttribute(name) == false) return def;
	   return xml.getAttribute(name);
   }
   /**
    * Speichert einen Double Wert in einem XML Knoten
    * @param xml XML Knoten
    * @param name Name des Attributs
    * @param value Der zu speichernde Wert
    */
   public static void writeDouble(Element xml, String name, double d)
   {
      xml.setAttribute(name, Double.toString(d));
   }
   /**
    * List einen Double Wert aus einem XML Knoten
    * @param xml XML Knoten 
    * @param name Name des Attributs
    * @param def Der Defaultwert, falls das Attribut nicht existiert
    * @return Der gelesen Wert.
    */
   public static double readDouble(Element xml, String name, double def)
   {
	   if (xml.hasAttribute(name) == false) return def;
      return Double.parseDouble(xml.getAttribute(name));
   }
   /**
    * Speichert einen Integer Wert in einem XML Knoten
    * @param xml XML Knoten
    * @param name Name des Attributs
    * @param value Der zu speichernde Wert
    */
   public static void writeInteger(Element xml, String name, int d)
   {
      xml.setAttribute(name, Integer.toString(d));
   }
   /**
    * List einen Integer Wert aus einem XML Knoten
    * @param xml XML Knoten 
    * @param name Name des Attributs
    * @param def Der Defaultwert, falls das Attribut nicht existiert
    * @return Der gelesen Wert.
    */
   public static int readInteger(Element xml, String name, int def)
   {
	   if (xml.hasAttribute(name) == false) return def;
      return Integer.parseInt(xml.getAttribute(name));
   }
   /**
    * Speichert einen Boolschen Wert in einem XML Knoten
    * @param xml XML Knoten
    * @param name Name des Attributs
    * @param value Der zu speichernde Wert
    */
   public static void writeBoolean(Element xml, String name, boolean d)
   {
      xml.setAttribute(name, Boolean.toString(d));
   }
   /**
    * List eine Boolschen Wert aus einem XML Knoten
    * @param xml XML Knoten 
    * @param name Name des Attributs
    * @param def Der Defaultwert, falls das Attribut nicht existiert
    * @return Der gelesen Wert.
    */
   public static boolean readBoolean(Element xml, String name, boolean def)
   {
	   if (xml.hasAttribute(name) == false) return def;
      return Boolean.getBoolean(xml.getAttribute(name));
   }
   /**
    * Diese Methode speichert ein Objekt in einem XML-Knoten. Das Objekt
    * kann dann sowohl mit readObjekt als auch mit readObjectMulti geladen
    * werden.
    * 
    * @param xml Der XML Knoten
    * @param name Name des Objekts
    * @param obj Das eigentliche Objekt
    * @param version Die Dateiversion
    */
   public static void writeObject(Element xml,String name, UtiOB obj, 
                                  int version)
   {
      Document d = xml.getOwnerDocument();
      Element sItem = d.createElement(name);
      xml.appendChild(sItem);
      obj.write(sItem, version);
   }
   /**
    * Diese lädt die Daten aus einem XML Knoten in ein !! bestehendes Objekt.
    * Diese muss natürlich den passenden Typ haben. Es wird kein neues Objekt erzeugt.
    * Ist dies gewünscht so sollte man readObjectMulti verwenden.
    * 
    * @param xml Der XML-Knoten.
    * @param name Name des Objekts
    * @param obj Das Objekt in dem die gelesenen Daten gespeichert werden
    * @param version Die Dateiversion
    */
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
   /**
    * Diese Methode erzeugt aus einem Xin jedem Element ML-Knoten ein neues Objekt und gibt dies
    * zurück. Der Klassentyp wird dabei durch den XML-Knoten bestimmt.
    * 
    * @param xml Das XML-Knoten
    * @param name Name des Objekts
    * @param version Dateiversion
    * @param Parent Das übergeordnete Objekt. Dieses wird mit setObjParent gesetzt.
    * @return Das neuerzeugt Objekt aus dem XML Knoten.
    */
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
   /**
    * Diese Methode schreibt Objekt aus einer Liste in einen XML Knoten. Diese
    * Objekte kann man dann mit readList wieder laden.
    * 
    * @param xml Der XML Knoten in den geschrieben werden soll.
    * @param name Name der Liste
    * @param Items Die eigentliche Liste
    * @param version Dateiversion
    */
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
   /**
    * Diese Methode lädt Objekte in eine Liste (Vector) hinein. Diese musste
    * vorher mit writeList geschreiben worden sein.
    * 
    * @param xml Der XML Knoten aus dem geladen wird.
    * @param name Der Name der Liste. So kann es mehrere Listen pro Klasse geben
    * @param Items Die Liste in der die Objekte eingefügt werden sollen.
    * @param version Die Version des Dateiformats
    * @param Parent Das übergeordnete Objekt der Elemente der Liste. Dieses wird
    * mit setObjParent in jedem Element gesetzt.
    */
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
   /**
    * Diese Methode speichert das Objekt in einem XML Knoten. Eigenschaften
    * werden dabei im Allgemeinen als Attribute und Unterobjekte als weitere
    * Knoten gespeichert
    * 
    * Im Attribut _id wird eine eindeutige ID für das Objekt gespeichert.
    * Diese bleibt nach dem neuladen erhalten.
    * 
    * Im Attribut _type wird der Klassentyp des Objekts gespeichert. Dieser
    * wrid dann beim Neuladen verwenden um mit java.lang.ref das entsprechende
    * Objekt zu erzeugen.
    * 
    * Unterobjekt sollten diese Methode überschreiben, damit ihre Eigenschaften
    * auch gespeichert werden.
    * 
    * @param xml XML Knoten
    * @param version Dateiversion
    */
   public void write(Element xml, int version)
   {
      // xml.set�
      Document d = xml.getOwnerDocument();
      //Attr a = d.createAttribute("id");
      //a.setValue(Long.toHexString(id));
      xml.setAttribute("_id", Long.toHexString(id));
      xml.setAttribute("_type", getTypeInfo());

   }
   /**
    * Diese Methode lädt ein Objekt aus einem XML-Knoten. Der Typ des Objekts, dass
    * in diesem Knoten gespeichert wurde, sollte mit dem übereinstimmen, dass zum 
    * laden verwendet wird.
    * 
    * Siehe auch void write(Element xml, int version)
    * 
    * Unterobjekte sollten diese Methode überschreiben, damit ihre Eigenschaften 
    * geladen werden können.
    * 
    * @param xml
    * @param version
    */
   public void read(Element xml, int version)
   {
      String d = xml.getAttribute("_id");
      setID(Long.parseLong(d, 16));
      loadobj.put(new Long(id), this);
   }
   /**
    * Diese Methode speicher das aktuelle Objekt in einer XML-Datei.
    * Was gespeichert wird bestimmt die write Methode.
    * @param name Name der XML-Datei
    */
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
   /**
    * Diese Methode lädt Daten aus eine XML in dieses Objekt.
    * Dabei wird der vorherige Inhalt der Datei vollständig ersetzt.
    * @param name Name der XML-Datei
    */
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
