package uti.codeModel.ExportCPP;



import uti.codeModel.*;
import uti.codeModel.UtiPackage;
import uti.codeModel.ExportBase.BaseExporter;
import uti.codeModel.ExportBase.CodeSys;
import uti.java.*;
import java.util.*;

class MakeTarget
{
	String name;
	Vector depends= new Vector();
}

public class CPPExporter extends BaseExporter {
    MakeTarget currenttarget = null;
    Vector targets = new Vector();
	public CPPExporter() {
		super();
		// TODO Auto-generated constructor stub
	}
    void addTarget(String str)
    {
    	currenttarget = new MakeTarget();
    	currenttarget.name = str;
    	targets.addElement(currenttarget);
    }
    void addDepends(String str)
    {
    	currenttarget.depends.addElement(str);
    }
	public String createPreview(BaseCode obj) {
		// TODO Auto-generated method stub
		return super.createPreview(obj);
	}

	public String getPackageDefine(UtiPackage pack) {
		if (pack == null)
			return "";
		String dir = getPackageDefine((UtiPackage) pack.getObjParent());
		return dir + pack.getName().toUpperCase() + "_";
	}

	public int generateNameSpaces(UtiPackage pack) {
		if (pack == null)
			return 0;
		int v = generateNameSpaces((UtiPackage) pack.getObjParent());
		if (v != 0)
			CodeSys.o().println("namespace " + pack.getName() + "{");
		return v + 1;
	}
	public int generateUsingIntern(UtiPackage pack) {
		if (pack == null)
			return 0;
		int v = generateUsingIntern((UtiPackage) pack.getObjParent());
		if (v != 0) {
			if (v != 1) CodeSys.o().print("::");
			CodeSys.o().print(pack.getName());
		}
		return v + 1;
	}
	void generateUsing(UtiPackage pack) {
		CodeSys.o().print("using namespace ");
		generateUsingIntern(pack);
		CodeSys.o().println(";");
	}
    void generateHeaderImport(UtiPackage own, ImportList l)
    {
    	//getPackageDir(pack)
    	Object [] p = l.getPrimary();
    	for (int i = 0; i < p.length; i++) {
    		if (p[i] instanceof UtiCollection) {
    		   UtiCollection cl = (UtiCollection)p[i];
   			   UtiPackage parent=null;
			   if (cl.getObjParent() instanceof UtiPackage)
		          parent = (UtiPackage)cl.getObjParent();
    		   if (parent != null) {
    			   String s = getPackageIncDir(parent)+cl.getName();
    			   addDepends(s);
    			   CodeSys.o().println("#include \"" + s + ".h\"");
    			   if (parent != own) {
    				   generateUsing(parent);
    			   }
    		   }
    		   
    		}
    	}
    	
    	p = l.getSecondary();
    	for (int i = 0; i < p.length; i++) {
    		if (p[i] instanceof UtiCollection) {
    			UtiCollection cl = (UtiCollection)p[i];
    			UtiPackage parent=null;
    			if (cl.getObjParent() instanceof UtiPackage)
    		       parent = (UtiPackage)cl.getObjParent();
    		   if (parent != null) {
    			   int level = generateNameSpaces(parent);
    			   CodeSys.o().println("class "+cl.getName()+";");
    			   for (int j = 0; j < level-1; j++) {
    					CodeSys.o().println("}");
    				}
    			   if (parent != own) {
    				   generateUsing(parent);
    			   }
    		   }
    		}
    	}
    	for (int i = 0; i < p.length; i++) {
    		if (p[i] instanceof UtiArray) {
    			UtiArray a = (UtiArray)p[i];
    			String Base = getTypeName(a.getBasetype());
    			String Name = getTypeNameIntern(a);
    			CodeSys.o().println("typedef _array<"+Base+"> "+Name+";");
    		}
    	}
    }
    
    void generateCppImport(UtiPackage own,ImportList l)
    {
    	Object [] p = l.getSecondary();
    	for (int i = 0; i < p.length; i++) {
    		if (p[i] instanceof UtiCollection) {
    			UtiCollection cl = (UtiCollection)p[i];
    			UtiPackage parent=null;
    			if (cl.getObjParent() instanceof UtiPackage)
    		       parent = (UtiPackage)cl.getObjParent();
    		   if (parent != null) {
    			   String s = getPackageIncDir(parent)+cl.getName()+"";
    			   addDepends(s);
    			   CodeSys.o().println("#include \"" + s + ".h\"");
    			   
    		   }
    		}
    	}
    }
	public void generateClass(UtiClass cl) {
		CodeSys.output(cl.getName() + ".h");

		UtiPackage p = (UtiPackage) cl.getObjParent();
		String def = getPackageDefine(p) + cl.getName().toUpperCase();
		CodeSys.o().println("#ifndef " + def);
		CodeSys.o().println("#define " + def);
		CodeSys.o().println();
		CodeSys.o().println("#include \"basesystem.h\"");
		addTarget(getPackageIncDir(p)+cl.getName());
		for (int i = 0; i < cl.getChildCount(); i++) {
			   Object o = cl.getChild(i);
			   if (o instanceof UtiExtern) {
				   generateExternIncl((UtiExtern)o);
			   }
		}
		CodeSys.o().println();
		ImportList l = new ImportList();
		cl.searchImports(l);
		generateHeaderImport(p, l);			CodeSys.o().println();
		int level = generateNameSpaces(p);
		CodeSys.o().println();
		CodeSys.o().print("class " + cl.getName());
		if (cl.getExtends() != null) {
			CodeSys.o().print(" : public " + cl.getExtends().getName());
		}
		
		CodeSys.o().println(" {");
		CodeSys.o().incDepth();
		CodeSys.o().println("public:");
		super.generateClass(cl);
		CodeSys.o().decDepth();
		CodeSys.o().println("");
		CodeSys.o().println("};");
		CodeSys.o().println();
		for (int i = 0; i < level-1; i++) {
			CodeSys.o().println("}");
		}
		CodeSys.o().println();
		CodeSys.o().println("#endif");
		CodeSys.output(cl.getName() + ".cpp");
		CodeSys.o().println("#include \"" + cl.getName() + ".h\"");
		generateCppImport(p,l);
		level = generateNameSpaces(p);
		CodeSys.o().println();
		for (int i = 0; i < cl.getChildCount(); i++) {
			Object o = cl.getChild(i);
			if (o instanceof UtiMethod) {
				generateMethodBody(cl, (UtiMethod) o);
			}
			;
		}

		CodeSys.o().println();
		for (int i = 0; i < level - 1; i++) {
			CodeSys.o().println("}");
		}
	}

	public void generateMethod(UtiMethod m) {
		if (!m.isConstructor()) {
			CodeSys.o().print("virtual ");
		   generateType(m.getResultType());
		   CodeSys.o().print(" ");
		}		printMethodHeader(m);
		CodeSys.o().println(";");
	}

	public void generateMethodBody(UtiClass parent, UtiMethod m) {
		CodeSys.o().println();
		if (!m.isConstructor()) {
		   generateType(m.getResultType());
		   CodeSys.o().print(" ");
		}
		CodeSys.o().print(parent.getName());
		CodeSys.o().print("::");
		printMethodHeader(m);
		CodeSys.o().println();
		generateBlock(m.getBlock());
	}

	void printMethodHeader(UtiMethod m) {
		CodeSys.o().print(m.getName() + "(");
		for (int i = 0; i < m.getChildCount(); i++) {
			printVar((UtiVariable) m.getChild(i));
			if (i != m.getChildCount() - 1) {
				CodeSys.o().print(", ");
			}
		}
		CodeSys.o().print(")");
	}

	public void generateElement(UtiOB o) {
		if (o instanceof UtiAusdruck) {
			generateAusdruck((UtiAusdruck) o);
		}
	}

	public void generateVarRef(UtiVariable v) {
		CodeSys.o().print(v.getName());
	}

	public void gen2Ausdruck(UtiAusdruck a, String name) {

		UtiOB el1 = a.getElement(0);
		UtiOB el2 = a.getElement(1);
		boolean klammer1 = false;
		boolean klammer2 = false;
		if (el1 instanceof UtiAusdruck
				&& ((UtiAusdruck) el1).getPrecedenceLevel() < a
						.getPrecedenceLevel()) {
			klammer1 = true;
		}
		if (el2 instanceof UtiAusdruck
				&& ((UtiAusdruck) el2).getPrecedenceLevel() < a
						.getPrecedenceLevel()) {
			klammer2 = true;
		}
		if (klammer1) {
			CodeSys.o().print("(");
		}
		generateElement(el1);
		if (klammer1) {
			CodeSys.o().print(") ");
		}
		CodeSys.o().print(" "+name+" ");
		if (klammer2) {
			CodeSys.o().print(" (");
		}
		generateElement(el2);
		if (klammer2) {
			CodeSys.o().print(")");
		}
	}

	public void gen3Ausdruck(UtiAusdruck a) {

		UtiOB el1 = a.getElement(0);
		UtiOB el2 = a.getElement(1);
		UtiOB el3 = a.getElement(2);
		boolean klammer1 = false;
		boolean klammer2 = false;
		boolean klammer3 = false;
		if (el1 instanceof UtiAusdruck
				&& ((UtiAusdruck) el1).getPrecedenceLevel() < a
						.getPrecedenceLevel()) {
			klammer1 = true;
		}
		if (el2 instanceof UtiAusdruck
				&& ((UtiAusdruck) el2).getPrecedenceLevel() < a
						.getPrecedenceLevel()) {
			klammer2 = true;
		}
		if (el3 instanceof UtiAusdruck
				&& ((UtiAusdruck) el3).getPrecedenceLevel() < a
						.getPrecedenceLevel()) {
			klammer3 = true;
		}
		if (klammer1) {
			CodeSys.o().print("(");
		}
		generateElement(el1);
		if (klammer1) {
			CodeSys.o().print(") ");
		}
		CodeSys.o().print(" ? ");
		if (klammer2) {
			CodeSys.o().print(" (");
		}
		generateElement(el2);
		if (klammer2) {
			CodeSys.o().print(")");
		}
		CodeSys.o().print(" : ");
		if (klammer3) {
			CodeSys.o().print(" (");
		}
		generateElement(el3);
		if (klammer3) {
			CodeSys.o().print(")");
		}
	}

	public void genFAusdruck(UtiAusdruck a, String name) {

		UtiOB el1 = a.getElement(0);
		boolean klammer1 = false;
		if (el1 instanceof UtiAusdruck
				&& ((UtiAusdruck) el1).getPrecedenceLevel() < a
						.getPrecedenceLevel()) {
			klammer1 = true;
		}
		CodeSys.o().print(name);
		if (klammer1) {
			CodeSys.o().print("(");
		}
		generateElement(el1);
		if (klammer1) {
			CodeSys.o().print(") ");
		}
	}

	public void genF2Ausdruck(UtiAusdruck a, String name) {

		UtiOB el1 = a.getElement(1);
		boolean klammer1 = false;
		if (el1 instanceof UtiAusdruck
				&& ((UtiAusdruck) el1).getPrecedenceLevel() < a
						.getPrecedenceLevel()) {
			klammer1 = true;
		}
		CodeSys.o().print(name);
		if (klammer1) {
			CodeSys.o().print("(");
		}
		generateElement(el1);
		if (klammer1) {
			CodeSys.o().print(") ");
		}
	}

	public void genBAusdruck(UtiAusdruck a, String name) {

		UtiOB el1 = a.getElement(0);
		boolean klammer1 = false;
		if (el1 instanceof UtiAusdruck
				&& ((UtiAusdruck) el1).getPrecedenceLevel() < a
						.getPrecedenceLevel()) {
			klammer1 = true;
		}
		if (klammer1) {
			CodeSys.o().print("(");
		}
		generateElement(el1);
		if (klammer1) {
			CodeSys.o().print(") ");
		}
		CodeSys.o().print(name);

	}

	public void generateAusdruck(UtiAusdruck a) {
		switch (a.getType()) {
		case UtiAusdruck.BI_VARIABLE: {
			Link n = (Link)a.getElement(0);
			CodeSys.o().print(((BaseName)n.getObject()).getName());

		};
		break;
		case UtiAusdruck.BI_CALL: 
		case UtiAusdruck.BI_CONSTRUCTOR: {
			Link n = (Link)a.getElement(0);
			if (a.getType() == UtiAusdruck.BI_CONSTRUCTOR) CodeSys.o().print("new ");
			CodeSys.o().print(((BaseName)n.getObject()).getName());
			CodeSys.o().print("(");
			for (int i = 1; i < a.getCount(); i++) {
				UtiAusdruck b = (UtiAusdruck)a.getElement(i);
				generateAusdruck(b);
				if (i != a.getCount()-1) {
					CodeSys.o().print(", ");
				}
			}
			CodeSys.o().print(")");
		};
		break;
		case UtiAusdruck.BI_CONSTRUCTORARRAY: {
			Link n = (Link)a.getElement(0);
			int dimensions = a.getCount()-1;
			UtiType typ = (UtiType)n.getObject();
			UtiType a2= UtiProgram.MainProg.getArrayType(typ, dimensions);
			if (dimensions == 1) {
				String name = getTypeNameIntern(a2);
				CodeSys.o().print("new "+name+"(");
				generateAusdruck((UtiAusdruck)a.getElement(1));
				CodeSys.o().print(")");
			}
			
		}; break;
		case UtiAusdruck.BI_ARRAY: {
			UtiAusdruck base = (UtiAusdruck)a.getElement(0);
			UtiAusdruck a1 = (UtiAusdruck)a.getElement(1);
			CodeSys.o().print("(*");
			if (base.getPrecedenceLevel() < 14) {
				CodeSys.o().print("(");
			}
			generateAusdruck(base);
			if (base.getPrecedenceLevel() < 14) {
				CodeSys.o().print(")");
			}
			CodeSys.o().print(")");
			CodeSys.o().print("[");
			generateAusdruck(a1);
			CodeSys.o().print("]");

		};
		break;
		case UtiAusdruck.BI_METHOD: {
			UtiAusdruck base = (UtiAusdruck)a.getElement(0);
			if (base.getPrecedenceLevel() < 14) {
				CodeSys.o().print("(");
			}
			generateAusdruck(base);
			if (base.getPrecedenceLevel() < 14) {
				CodeSys.o().print(")");
			}
			CodeSys.o().print("->");
			Link n = (Link)a.getElement(1);
			CodeSys.o().print(((BaseName)n.getObject()).getName());
			CodeSys.o().print("(");
			for (int i = 1; i < a.getCount(); i++) {
				UtiAusdruck b = (UtiAusdruck)a.getElement(i);
				generateAusdruck(b);
				if (i != a.getCount()-1) {
					CodeSys.o().print(", ");
				}
			}
			CodeSys.o().print(")");
		};
		break;
		case UtiAusdruck.BI_FIELD: {
			UtiAusdruck base = (UtiAusdruck)a.getElement(0);
			if (base.getPrecedenceLevel() < 14) {
				CodeSys.o().print("(");
			}
			generateAusdruck(base);
			if (base.getPrecedenceLevel() < 14) {
				CodeSys.o().print(")");
			}
			CodeSys.o().print("->");
			Link n = (Link)a.getElement(1);
			CodeSys.o().print(((BaseName)n.getObject()).getName());
		};
		break;
		case UtiAusdruck.BI_PLUS: {
			gen2Ausdruck(a, "+");

		}
			;
			break;
		case UtiAusdruck.BI_MINUS: {
			gen2Ausdruck(a, "-");

		}
			;
			break;
		case UtiAusdruck.BI_MULTIPLY: {
			gen2Ausdruck(a, "*");

		}
			;
			break;
		case UtiAusdruck.BI_DIVIDE: {
			gen2Ausdruck(a, "/");

		}
			;
			break;
		case UtiAusdruck.BI_NEG: {
			gen2Ausdruck(a, "-");

		}
			;
			break;
		case UtiAusdruck.BI_OR: {
			gen2Ausdruck(a, "|");

		}
			;
			break;
		case UtiAusdruck.BI_LOGICOR: {
			gen2Ausdruck(a, "||");

		}
			;
			break;
		case UtiAusdruck.BI_LOGICAND: {
			gen2Ausdruck(a, "&&");

		}
			;
			break;
		case UtiAusdruck.BI_AND: {
			gen2Ausdruck(a, "&");

		}
			;
			break;
		case UtiAusdruck.BI_NOT: {
			genFAusdruck(a, "!");

		}
			;
			break;
		case UtiAusdruck.BI_XOR: {
			gen2Ausdruck(a, "^");

		}
			;
			break;
		case UtiAusdruck.BI_LSHIFT: {
			gen2Ausdruck(a, "<<");

		}
			;
			break;
		case UtiAusdruck.BI_RSHIFT: {
			gen2Ausdruck(a, ">>");

		}
			;
			break;
		case UtiAusdruck.BI_SHIFTROUND: {
			gen2Ausdruck(a, ">>>");

		}
			;
			break;
		case UtiAusdruck.BI_EQUAL: {
			gen2Ausdruck(a, "==");

		}
			;
			break;
		case UtiAusdruck.BI_NOTEQUAL: {
			gen2Ausdruck(a, "!=");

		}
			;
			break;
		case UtiAusdruck.BI_LESS: {
			gen2Ausdruck(a, "<");

		}
			;
			break;
		case UtiAusdruck.BI_ELESS: {
			gen2Ausdruck(a, "<=");

		}
			;
			break;
		case UtiAusdruck.BI_GREATER: {
			gen2Ausdruck(a, ">");

		}
			;
			break;
		case UtiAusdruck.BI_EGREATER: {
			gen2Ausdruck(a, ">=");

		}
			;
			break;
		case UtiAusdruck.BI_INSTANCEOF: {
			// gen2Ausdruck(a, "(dy");
			CodeSys.o().print("(dynamic_cast<");
			generateType((UtiType)((Link) a.getElement(0)).getObject());
			CodeSys.o().print(">(");
			generateAusdruck((UtiAusdruck) a.getElement(1));
			CodeSys.o().print(")");
		}
			;
			break;
		case UtiAusdruck.BI_MOD: {
			gen2Ausdruck(a, "%");

		}
			;
			break;
		case UtiAusdruck.BI_BITNOT: {
			genFAusdruck(a, "~");

		}
			;
			break;
		case UtiAusdruck.BI_LEFTINCREMENT: {
			genFAusdruck(a, "++");

		}
			;
			break;
		case UtiAusdruck.BI_RIGHTINCREMENT: {

			genBAusdruck(a, "++");
		}
			;
			break;
		case UtiAusdruck.BI_LEFTDECREMENT: {

			genFAusdruck(a, "--");
		}
			;
			break;
		case UtiAusdruck.BI_RIGHTDECREMENT: {
			genBAusdruck(a, "--");

		}
			;
			break;
		case UtiAusdruck.BI_TRUE: {
			CodeSys.o().print("true");

		}
			;
			break;
		case UtiAusdruck.BI_FALSE: {
			CodeSys.o().print("false");

		}
			;
			break;
		case UtiAusdruck.BI_NULL: {
			CodeSys.o().print("NULL");

		}
			;
			break;
		case UtiAusdruck.BI_CAST: {
			CodeSys.o().print("(");
			generateType(((UtiType)((Link) a.getElement(0)).getObject()));
			CodeSys.o().print(")");
			genF2Ausdruck(a, "");

		}
			;
			break;
		case UtiAusdruck.BI_INT: {
			CodeSys.o().print(a.getElement(0).toString());

		}
			;
			break;
		case UtiAusdruck.BI_FLOAT: {
			CodeSys.o().print(a.getElement(0).toString());

		}
			;
			break;
		case UtiAusdruck.BI_STRING: {
			CodeSys.o().print("new String(new _array<wchar_t>(L\"" + a.getElement(0).toString() + "\", "+a.getElement(0).toString().length()+"))");

		}
			;
			break;
		case UtiAusdruck.BI_CHAR: {
			CodeSys.o().print("'" + a.getElement(0).toString() + "'");

		}
			;
			break;
		/*
		 * case UtiAusdruck.BI_DESIGNATOR: {
		 * generateDesignator((UtiDesignator)a.getElement(0));
		 *  }; break;
		 */
		case UtiAusdruck.BI_QUESTION: {
			gen3Ausdruck(a);

		}
			;
			break;
		case UtiAusdruck.BI_SET: {
			gen2Ausdruck(a, "=");

		}
			;
			break;
		case UtiAusdruck.BI_SETADD: {
			gen2Ausdruck(a, "+=");

		}
			;
			break;
		case UtiAusdruck.BI_SETSUB: {
			gen2Ausdruck(a, "-=");

		}
			;
			break;
		case UtiAusdruck.BI_SETMUL: {
			gen2Ausdruck(a, "*=");

		}
			;
			break;
		case UtiAusdruck.BI_SETDIV: {
			gen2Ausdruck(a, "/=");

		}
			;
			break;
		case UtiAusdruck.BI_SETMODULO: {
			gen2Ausdruck(a, "%=");

		}
			;
			break;
		case UtiAusdruck.BI_SETRSHIFT: {
			gen2Ausdruck(a, ">>=");

		}
			;
			break;
		case UtiAusdruck.BI_SETRROUNDSHIFT: {
			gen2Ausdruck(a, ">>>=");

		}
			;
			break;
		case UtiAusdruck.BI_SETLSHIFT: {
			gen2Ausdruck(a, "<<=");

		}
			;
			break;
		case UtiAusdruck.BI_SETAND: {
			gen2Ausdruck(a, "&=");

		}
			;
			break;
		case UtiAusdruck.BI_SETOR: {
			gen2Ausdruck(a, "|=");

		}
			;
			break;
		case UtiAusdruck.BI_SETXOR: {
			gen2Ausdruck(a, "^=");

		}
			;
			break;
		default: {

		}
			;

		}
	}
    
	public void generateSpecialCommand(UtiSpecialCommand w)
	{
		if (w.getType() == UtiSpecialCommand.RETURN) {
			CodeSys.o().print("return");
			if (w.getAusdruck() != null) {
				CodeSys.o().print(" ");
				generateAusdruck(w.getAusdruck());
			}
			CodeSys.o().println(";");
		} else if (w.getType() == UtiSpecialCommand.BREAK) {
			CodeSys.o().println("break;");
		} else if (w.getType() == UtiSpecialCommand.CONTINUE) {
			CodeSys.o().println("continue;");
		};
	}
	
	public void generateIf(UtiIf ifobj) {
		CodeSys.o().print("if (");
		generateAusdruck(ifobj.getExpression());
		CodeSys.o().print(") ");
		generateBlock(ifobj.getThen_Block());
		if (ifobj.getElse_Block() != null) {
			CodeSys.o().print("else");
			generateBlock(ifobj.getElse_Block());
		}
	}

	public void generateWhile(UtiWhile w) {
		
		if (w.isDoWhile()) {
			CodeSys.o().print("do");
			generateBlock(w.getBlock());
			CodeSys.o().print("while (");
			generateAusdruck(w.getExpression());
			CodeSys.o().print(") ");
		} else {
		   CodeSys.o().print("while (");
		   generateAusdruck(w.getExpression());
		   CodeSys.o().print(") ");
		   generateBlock(w.getBlock());
		}
	}
	public void generateFor(UtiFor f)
	{
		CodeSys.o().print("for (");
		for (int i = 0; i < f.getInitCount(); i++) {
			if (i != 0) CodeSys.o().print(", ");
			BaseCode c = f.getInit(i);
			if (c instanceof UtiVariable) {
				printVar((UtiVariable)c);
			} else {
				generateAusdruck((UtiAusdruck)c);
			}
		}
		CodeSys.o().print(";");
		generateAusdruck(f.getBedingung());
		CodeSys.o().print(";");
		for (int i = 0; i < f.getUpdateCount(); i++) {
			if (i != 0) CodeSys.o().print(", ");
			generateAusdruck(f.getUpdate(i));
		}
		CodeSys.o().print(")");
		generateBlock(f.getBlock());
	}
	public void generateExtern(UtiExtern w)
	{
		  if (w.getType().equals("C++")) {
			  CodeSys.o().print(w.getCode());
		  }
	}
	public void generateExternIncl(UtiExtern w)
	{
		  if (w.getType().equals("H++")) {
			  CodeSys.o().print(w.getCode());
		  }
	}
	public void printVar(UtiVariable var) {
		generateType(var.getType());
		CodeSys.o().print(" " + var.getName());
	}

	public void generateVariable(UtiVariable var) {
		printVar(var);
		CodeSys.o().println(";");
	}
    
	public void generateType(UtiType desc) {
		String typename;
		typename = getTypeName(desc);
		
		CodeSys.o().print(typename);
	}

	String getTypeName(UtiType desc)
	{
		String s = getTypeNameIntern(desc);
		if (desc instanceof UtiArray) {
			s += "*";
		}
		return s;
	}
	String getTypeNameIntern(UtiType desc) {
		String typename = "nix";
		if (desc == null) {
			typename = "void";
		} else if (desc instanceof BaseType) {
			int type = ((BaseType) desc).getType();
			if (type == BaseType.BT_BOOL)
				typename = "bool";
			if (type == BaseType.BT_BYTE)
				typename = "char";
			if (type == BaseType.BT_CHAR)
				typename = "wchar_t";
			if (type == BaseType.BT_DOUBLE)
				typename = "double";
			if (type == BaseType.BT_FLOAT)
				typename = "float";
			if (type == BaseType.BT_INT)
				typename = "int";
			if (type == BaseType.BT_LONG)
				typename = "long long";
			if (type == BaseType.BT_SHORT)
				typename = "short";
			//typename = desc.getType().getName();

		} else if (desc instanceof UtiArray) {
			UtiArray a = (UtiArray)desc;
           typename = "AR_"+getTypeName(a.getBasetype());
           
		} else {
			typename = desc.getName();
			if (desc instanceof UtiCollection) {
				typename += "*";
			}
		}
		;
		return typename;
	}

	public void generateInterface(UtiInterface in) {

	}

	public void generatePackage(UtiPackage pack) {
		for (int i = 0; i < pack.getPackageCount(); i++) {
			UtiPackage p = pack.getPackage(i);
			generatePackage(p);
		}
		CodeSys.setOutputDir(getPackageDir(pack));
		for (int i = 0; i < pack.getChildCount(); i++) {
			if (pack.getChild(i) instanceof UtiClass) {
				generateClass((UtiClass) pack.getChild(i));
			}
			if (pack.getChild(i) instanceof UtiInterface) {
				generateInterface((UtiInterface) pack.getChild(i));
			}
		}

	}

	public void generateBlock(UtiBlock b) {
		CodeSys.o().incDepth();
		CodeSys.o().println("{");
		super.generateBlock(b);
		CodeSys.o().decDepth();
		CodeSys.o().println();
		CodeSys.o().println("}");
	}

	public void export(UtiPackage pack) {
		// TODO Auto-generated method stub
		generatePackage(pack);
		
		CodeSys.setOutputDir("Base/");
		CodeSys.output("makefile");
		CodeSys.o().println("CC = g++");
		CodeSys.o().println("CFLAGS = -Wall -I./");
		CodeSys.o().print("OBJ = ");
		for (int i = 0; i < targets.size(); i++)
		{
			MakeTarget t = (MakeTarget)targets.elementAt(i);
			CodeSys.o().print(t.name+".o ");
		}
		CodeSys.o().println("maincall.o");
		CodeSys.o().println();
		String progname="prog";
		CodeSys.o().println(progname+": $(OBJ)"); //Später durch MainFunc
		CodeSys.o().println("	$(CC) $(CFLAGS) -o "+progname+" $(OBJ)");
		CodeSys.o().println();

		for (int i = 0; i < targets.size(); i++)
		{
			MakeTarget t = (MakeTarget)targets.elementAt(i);
			CodeSys.o().print(t.name+".o: "+t.name+".cpp ");
			for (int j = 0; j < t.depends.size(); j++) {
				String s = (String)t.depends.elementAt(j);
				CodeSys.o().print(s+".h ");
			}
			CodeSys.o().println("basesystem.h");
			CodeSys.o().println("	$(CC) $(CFLAGS) -c "+t.name+".cpp -o "+t.name+".o");
			CodeSys.o().println();
		}
		CodeSys.o().println();
		CodeSys.o().println("maincall.o: ");
		CodeSys.o().println("	$(CC) $(CFLAGS) -c maincall.cpp");
		CodeSys.o().println();
		CodeSys.o().println(".PHONY: clean");
		CodeSys.o().println();
		CodeSys.o().println("clean:");
		CodeSys.o().println("	rm -f $(OBJ)");
		CodeSys.o().println();
		CodeSys.output("maincall.cpp");
		CodeSys.o().println("#include <iostream>");
		CodeSys.o().println("using namespace std;");
		CodeSys.o().println("int main(int argc, char* argv[]) ");
		CodeSys.o().println("{");
		CodeSys.o().println("   cout << \"Dies ist ein Test.\n\";");
		CodeSys.o().println("   return 0;");
		CodeSys.o().println("}");
		CodeSys.output("basesystem.h");
		CodeSys.o().println("#ifndef __BASESYSTEM__");
		CodeSys.o().println("#define __BASESYSTEM__");
		CodeSys.o().println("#include <stddef.h>");
		CodeSys.o().println("template<class T> class _array {");
		CodeSys.o().println("private:");
		CodeSys.o().println("   T* array;");
		CodeSys.o().println("public:");
		CodeSys.o().println("   int length;");
		CodeSys.o().println("   _array(int size)");
		CodeSys.o().println("   {");
		CodeSys.o().println("      array = new T[size];");
		CodeSys.o().println("      size = length;");
		CodeSys.o().println("   }");
		CodeSys.o().println("   T &operator[](unsigned int p)");
		CodeSys.o().println("   {");
		CodeSys.o().println("      return array[p];");
		CodeSys.o().println("   }");
		CodeSys.o().println("   const T &operator[](unsigned int p) const");
		CodeSys.o().println("   {");
		CodeSys.o().println("      return array[p];");
		CodeSys.o().println("   }");
		CodeSys.o().println("   _array(T* a, int size)");
		CodeSys.o().println("   {");
		CodeSys.o().println("      array = new T[size];");
		CodeSys.o().println("      size = length;");
		CodeSys.o().println("      for (int i = 0; i < size; i++) array[i] = a[i];");
		CodeSys.o().println("   }");
		CodeSys.o().println("};");
		CodeSys.o().println("#endif");
	}

}
