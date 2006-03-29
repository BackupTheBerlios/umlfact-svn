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
	boolean continueDesignator;
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

	static void add(Link l, BaseCode code, LinkName ln, Class expected) {
		SimpleLink link = new SimpleLink();
		link.l = l;
		link.code = code;
		link.ln = ln;
		link.expected = expected;
		links.addElement(link);
	}

	static void addDesignator(UtiDesignator d, Vector el) {
		DesignatorLink link = new DesignatorLink();
		link.d = d;
		link.el = el;
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
				// Oh je, dass kommt später
				return null;
			} else {
				return searchLinkb(obj.getObjParent(), name);

			}

		} else {
			return searchLinkb(obj.getObjParent(), name);

		}
	}

	static HeadResult searchHeadVar(UtiDesignator des, LinkName n)
			throws SyntaxException {
		int pos = 1;
		UtiOB result = null;
		for (int x = 1; x <= n.getCount(); x++) {
			pos = x;
			LinkName nt = new LinkName();
			for (int y = 0; y < x; y++)
				nt.addValue(n.getValue(y), n.getToken(y));
			result = searchLink(des, nt);
			if (!(result instanceof UtiVariable))
				result = null;
			if (result != null) {
				break;
			}

		}
		if (result == null) {
			throw new SyntaxException("Unbekannter Bezeichner: " + n.toString());
		}
		HeadResult res = new HeadResult();
		res.name = (BaseName) result;
		res.pos = pos;
		return res;
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
					link.l.setObject(ob);
				}
			} else if (o instanceof DesignatorLink) {
				DesignatorLink link = (DesignatorLink) o;
				UtiDesignator des = link.d;
				if (link.el.size() == 0) {
					throw new SyntaxException("Leerer Designator");
				}
				Object obj = link.el.elementAt(0);
				if (!(obj instanceof LinkName)) {
					throw new SyntaxException("Bezeichner erwartet");
				}
				LinkName n = (LinkName) obj;
				HeadResult res = searchHeadVar(des, n);
				UtiVariable var;
				Vector accesslist = new Vector();
				int element = 0;
				int elementpos = res.pos;
				final int MEMBER = 0;
				// final int CALL = 1;
				final int ARRAY = 2;
				final int NONE = 3;

				int type = MEMBER;
				TypeDescription result;
				if (res.name instanceof UtiMethod) {
					if (res.pos != n.getCount()) {
						throw new SyntaxException("Bezeichner erwartet");
					}
					;
					if (link.el.size() < 2
							|| (!(link.el.elementAt(1) instanceof Vector))) {
						throw new SyntaxException("Parameter erwartet");
					}
					LinkName this_var = new LinkName();
					des.setBase((UtiVariable) searchLink(des, this_var));
					des.addMethodAccess((UtiMethod) res.name, (Vector) link.el
							.elementAt(1));
					result = ((UtiMethod) res.name).getResultType();
					element = 2;
					elementpos = 0;
					type = NONE;
				} else {
					des.setBase((UtiVariable)res.name);
					result = ((UtiVariable)res.name).getDescription();
				}
				;
				ArrayAccess ac = null;
				while (element < link.el.size()) {
					Object el = link.el.elementAt(element);
					switch (type) {
					case MEMBER: {
						if (elementpos < n.getCount()) {
							if (elementpos == n.getCount() - 1
									&& element + 1 < link.el.size()
									&& link.el.elementAt(element + 1) instanceof Vector) {
								MethodAccess ma = new MethodAccess();
								ma.name = n.getValue(elementpos);
								ma.parameters = (Vector) link.el
										.elementAt(element + 1);
								accesslist.add(ma);
							} else {

								MemberAccess m = new MemberAccess();
								m.name = n.getValue(elementpos);
								accesslist.add(m);
								elementpos++;
							}
							;
						} else {
							element++;
							elementpos = 0;
							type = NONE;
						}
					}
						;
						break;
					case ARRAY: {
						if (el instanceof UtiAusdruck) {
							ac.ausdrucke.addElement(el);
							element++;
						} else {
							element++;
							type = NONE;
						}
					}
						;
						break;
					case NONE: {

						if (el instanceof LinkName) {
							type = MEMBER;
							n = (LinkName) el;
						} else if (el instanceof UtiAusdruck) {
							type = ARRAY;
							ac = new ArrayAccess();
							accesslist.add(ac);
						}

					}
						;
						break;
					}
				}
				for (int x = 0; x < accesslist.size(); x++)
				{
					Object el = accesslist.elementAt(x);
					if (el instanceof ArrayAccess) {
						ac = (ArrayAccess)el;
						if (result.getArraySize() != ac.ausdrucke.size()) {
							throw new SyntaxException("Array mit "+ac.ausdrucke.size()+" Dimensionen erwartet");
						}
						des.addArrayAccess((UtiAusdruck[])ac.ausdrucke.toArray());
						TypeDescription newtype= new TypeDescription();
						newtype.setType(result.getType());
						result = newtype;
					} else {
						String str;
						if (el instanceof MemberAccess) {
							str = ((MemberAccess)el).name;
						} else {
							str = ((MethodAccess)el).name;
						}
						if (result.getArraySize() != 0) {
							throw new SyntaxException("Hier sollte kein Array stehen");
						};
					    UtiType typ= result.getType();
					    if (!(typ instanceof UtiCollection)) {
					    	throw new SyntaxException("Diese Klasse enthält keine Member");
					    };
					    UtiCollection coltype = (UtiCollection)typ;
					    BaseName namedobj = coltype.getChildByName(str);
					    if (namedobj == null) {
							throw new SyntaxException("Member existiert nicht: "+str);
						}
					    if (el instanceof MemberAccess) {
							if (!(namedobj instanceof UtiVariable)) {
								throw new SyntaxException("Hier sollte eine Variable sein");
							}
							des.addFieldAccess((UtiVariable)namedobj);
							result = ((UtiVariable)namedobj).getDescription();
						} else {
							MethodAccess method = (MethodAccess)el;
							if (!(namedobj instanceof UtiMethod)) {
								throw new SyntaxException("Hier sollte eine Methode sein");
							}
							if (method.parameters.size() != ((UtiMethod)namedobj).getParamCount()) {
								throw new SyntaxException("Die Anzahl der Parameter unterscheidet sich");
							};
							des.addMethodAccess((UtiMethod)namedobj, method.parameters);
							result = ((UtiMethod)namedobj).getResultType();
						}
					}
				}
			}

		}

		links.clear();
	}
}
