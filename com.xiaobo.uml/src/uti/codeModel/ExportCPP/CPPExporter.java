package uti.codeModel.ExportCPP;

import uti.codeModel.*;
import uti.codeModel.UtiPackage;
import uti.codeModel.ExportBase.BaseExporter;
import uti.codeModel.ExportBase.CodeSys;
import uti.java.*;

public class CPPExporter extends BaseExporter {

	public CPPExporter() {
		super();
		// TODO Auto-generated constructor stub
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

	public void generateClass(UtiClass cl) {
		CodeSys.output(cl.getName() + ".h");

		UtiPackage p = (UtiPackage) cl.getObjParent();
		String def = getPackageDefine(p) + cl.getName().toUpperCase();
		CodeSys.o().println("#ifndef " + def);
		CodeSys.o().println("#define " + def);
		CodeSys.o().println();
		for (int i = 0; i < cl.getChildCount(); i++) {
			   Object o = cl.getChild(i);
			   if (o instanceof UtiExtern) {
				   generateExternIncl((UtiExtern)o);
			   }
		}
		CodeSys.o().println();
		int level = generateNameSpaces(p);
		CodeSys.o().println();
		CodeSys.o().println("class " + cl.getName() + " {");
		super.generateClass(cl);

		CodeSys.o().println("}");
		CodeSys.o().println();
		for (int i = 0; i < level; i++) {
			CodeSys.o().println("}");
		}
		CodeSys.o().println();
		CodeSys.o().println("#endif");
		CodeSys.output(cl.getName() + ".cpp");
		CodeSys.o().println("#include \"" + cl.getName() + ".h\"");

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
		CodeSys.o().println();
		generateType(m.getResultType());
		CodeSys.o().print(" ");

		printMethodHeader(m);
		CodeSys.o().println(";");
	}

	public void generateMethodBody(UtiClass parent, UtiMethod m) {
		CodeSys.o().println();
		generateType(m.getResultType());
		CodeSys.o().print(" ");
		CodeSys.o().print(parent.getName());
		CodeSys.o().print("::");
		printMethodHeader(m);
		CodeSys.o().println();
		generateBlock(m.getBlock());
	}

	void printMethodHeader(UtiMethod m) {
		CodeSys.o().print(m.getName() + "(");
		for (int i = 0; i < m.getChildCount(); i++) {
			generateVariable((UtiVariable) m.getChild(i));
			if (i != m.getChildCount() - 1) {
				CodeSys.o().print(", ");
			}
		}
		CodeSys.o().print(")");
	}

	public void generateElement(UtiOB o) {
		if (o instanceof UtiAusdruck) {
			generateAusdruck((UtiAusdruck) o);
		} else if (o instanceof UtiDesignator) {
			generateDesignator((UtiDesignator) o);
		}
	}

	public void generateVarRef(UtiVariable v) {
		CodeSys.o().print(v.getName());
	}

	public void generateDesignator(UtiDesignator des) {
		BaseCommand com = des.getBase();
		if (com instanceof UtiVariable) {
			generateVarRef((UtiVariable) com);
		} else {
			System.out.print("(");
			generateAusdruck((UtiAusdruck) com);
			System.out.print(")");
		}

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
		CodeSys.o().print(name);
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
		case UtiAusdruck.BI_CALL: {
			Link n = (Link)a.getElement(0);
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
		case UtiAusdruck.BI_ARRAY: {
			UtiAusdruck b = (UtiAusdruck)a.getElement(0);
			CodeSys.o().print("[");
			generateAusdruck(b);
			CodeSys.o().print("]");

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
			generateType((TypeDescription) a.getElement(0));
			CodeSys.o().print(">(");
			generateElement((TypeDescription) a.getElement(1));
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
			CodeSys.o().println("true");

		}
			;
			break;
		case UtiAusdruck.BI_FALSE: {
			CodeSys.o().println("false");

		}
			;
			break;
		case UtiAusdruck.BI_NULL: {
			CodeSys.o().println("null");

		}
			;
			break;
		case UtiAusdruck.BI_CAST: {
			CodeSys.o().print("(");
			generateType((TypeDescription) a.getElement(0));
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
			CodeSys.o().print("\"" + a.getElement(0).toString() + "\"");

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
				CodeSys.o().print("return ");
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
		generateType(var.getDescription());
		CodeSys.o().print(" " + var.getName());
	}

	public void generateVariable(UtiVariable var) {
		printVar(var);
		CodeSys.o().println(";");
	}

	public void generateType(TypeDescription desc) {
		String typename = "nix";
		if (desc.getType() == null) {
			typename = "void";
		} else if (desc.getType() instanceof BaseType) {
			int type = ((BaseType) desc.getType()).getType();
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
			typename = desc.getType().getName();

		} else {
			typename = desc.getType().getName();
			if (desc.getType() instanceof UtiCollection) {
				typename += "*";
			}
		}
		;
		CodeSys.o().print(typename);
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
	}

}
