package uti.parser;

import java.util.Vector;

import uti.codeModel.*;

import uti.java.*;

class SimpleLink {
	Link l;
	BaseCode code;
	LinkName ln;
	Class expected;
	UtiAusdruck base=null;
	int arraysize;
}

class HeadResult {
	BaseName name;

	int pos;
}

class AccessElement {

}

class MemberAccess extends AccessElement {
	String name;
}

class MethodAccess extends AccessElement {
	String name;

	Vector parameters;
}

class ArrayAccess extends AccessElement {
	Vector ausdrucke = new Vector();
}

public class LinkMemory {
	static Vector links = new Vector();

	static void add(Link l, int arraysize, BaseCode code, LinkName ln, Class expected) {
		SimpleLink link = new SimpleLink();
		link.l = l;
		link.code = code;
		link.ln = ln;
		link.arraysize = arraysize;
		link.expected = expected;
		links.addElement(link);
	}
	
	static void add(Link l, BaseCode code, LinkName ln, Class expected) {
		SimpleLink link = new SimpleLink();
		link.l = l;
		link.code = code;
		link.ln = ln;
		link.arraysize = -1;
		link.expected = expected;
		links.addElement(link);
	}
	
	static void addBase(UtiAusdruck base, Link l, BaseCode code, LinkName ln, Class expected) {
		SimpleLink link = new SimpleLink();
		link.l = l;
		link.code = code;
		link.ln = ln;
		link.arraysize = -1;
		link.expected = expected;
		link.base = base;
		links.addElement(link);
	}


	static UtiOB searchLink(UtiOB obj, LinkName name, Class expected) throws SyntaxException {
		if (name.getCount()==1) {
			String str=name.getValue(0);
			if (str.equals("char")) return BaseType.bt_char;
			if (str.equals("boolean")) return BaseType.bt_bool;
			if (str.equals("byte")) return BaseType.bt_byte;
			if (str.equals("void")) return null;
			if (str.equals("short")) return BaseType.bt_short; 
			if (str.equals("int")) return BaseType.bt_int;
			if (str.equals("long")) return BaseType.bt_long;
			if (str.equals("float")) return BaseType.bt_float;
			if (str.equals("double")) return BaseType.bt_double;
			
			
		}
		return searchLinkb(obj, name, expected);
	}

	static UtiOB searchLinkb(UtiOB obj, LinkName name, Class expected) throws SyntaxException {
		if (obj == null)
			return null;
		if (obj instanceof BaseCollection) {
			BaseCollection coll = (BaseCollection) obj;
			BaseName n = coll.getChildByName(name.getValue(0));
			if (n != null && expected.isInstance(n)) {
				if (name.getCount() == 1)
					return (UtiOB) n;
				// throw new SyntaxException("Unbekanntes Element:
				// "+name.getValue(1));
				return null;
			} else if (obj instanceof UtiPackage) {
				//Erstmal im Basispacket suchen
				UtiPackage pjava=(UtiPackage)UtiProgram.MainProg.getBase().getPackageByName("java");
				if (pjava != null) {
					UtiPackage plang = pjava.getPackageByName("lang");
					if (plang != null) {
						n = plang.getChildByName(name.getValue(0));
						if (n != null) {
							if (name.getCount() == 1)
								return (UtiOB) n;
	
							return null;						
						}
					}
				}
				return null;
			} else {
				return searchLinkb(obj.getObjParent(), name, expected);

			}

		} else {
			return searchLinkb(obj.getObjParent(), name, expected);

		}
	}
    public static String getPos(Token t)
    {
    	return ""+t.beginLine+":"+t.beginColumn+"-"+t.endLine+":"+t.endColumn; 
    }
	public static void doLink() throws SyntaxException {
		for (int i = 0; i < links.size(); i++) {
			Object o = links.elementAt(i);
			if (o instanceof SimpleLink) {
				SimpleLink link = (SimpleLink) o;
				if (link.ln.getCount() == 1
						&& link.ln.getValue(0).equals("void")) {
					link.l.setObject(null);
					continue;
				}
				if (link.base != null) {
					UtiType t = link.base.getReturnType();
					if (!(t instanceof BaseCollection)) {
						throw new SyntaxException("Klasse erwartet: ");
					}
					BaseCollection coll = (BaseCollection)t;
					BaseName n = coll.getChildByName(link.ln.getValue(0));
					if (n == null) {
						throw new SyntaxException("Unbekanntes Feld: " +
								link.ln.getValue(0)+ " an " + 
								getPos(link.ln.getToken(0)));
					}
					if (!link.expected.isInstance(n)) {
						throw new SyntaxException("Erwartet: "
								+ link.expected.toString() +" statt " +n.toString() + " an " + getPos(link.ln.getToken(0))+" statt "+link.ln.getValue(0));
					}
					link.l.setObject((UtiOB)n);
				} else {
				UtiOB ob = searchLink((UtiOB) link.code, link.ln, link.expected);
				if (ob == null) {
					throw new SyntaxException("Unbekannter Bezeichner: "
							+ link.ln.toString()+ " an " + getPos(link.ln.getToken(0)));
				} else if (!link.expected.isInstance(ob)) {
					throw new SyntaxException("Erwartet: "
							+ link.expected.toString());
				} else {
					if (link.arraysize > 0) {
						ob = UtiProgram.MainProg.getArrayType((UtiType)ob, link.arraysize);
					}
					link.l.setObject(ob);
				}
				}
			}
		}
		links.clear();
	}
}
