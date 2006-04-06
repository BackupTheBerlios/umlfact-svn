package uti.parser;

import java.util.Vector;

import uti.codeModel.*;

import uti.java.*;

class SimpleLink {
	Link l;
	BaseCode code;
	LinkName ln;
	Class expected;
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

	static UtiOB searchLink(UtiOB obj, LinkName name) throws SyntaxException {
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
		return searchLinkb(obj, name);
	}

	static UtiOB searchLinkb(UtiOB obj, LinkName name) throws SyntaxException {
		if (obj == null)
			return null;
		if (obj instanceof BaseCollection) {
			BaseCollection coll = (BaseCollection) obj;
			BaseName n = coll.getChildByName(name.getValue(0));
			if (n != null) {
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
				return searchLinkb(obj.getObjParent(), name);

			}

		} else {
			return searchLinkb(obj.getObjParent(), name);

		}
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
				UtiOB ob = searchLink((UtiOB) link.code, link.ln);
				if (ob == null) {
					throw new SyntaxException("Unbekannter Bezeichner: "
							+ link.ln.toString());
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
		links.clear();
	}
}
