package uti.codeModel;

import java.util.*;

import org.w3c.dom.Element;

import uti.codeModel.ExportBase.CodeSys;
import uti.java.*;

public class UtiAusdruck extends UtiCommand {	
	public final static int BI_PLUS = 1100+0;
	public final static int BI_MINUS = 1100+1;
	public final static int BI_MULTIPLY = 1200+2;
	public final static int BI_DIVIDE = 1200+3;
	public final static int BI_NEG = 1300+4;
	public final static int BI_OR = 500+5;
	public final static int BI_LOGICOR = 300+6;
	public final static int BI_LOGICAND = 400+7;
	public final static int BI_AND = 700+8;
	public final static int BI_NOT = 1300+9;
	public final static int BI_XOR = 600+10;
	public final static int BI_LSHIFT = 1000+11;
	public final static int BI_RSHIFT = 1000+12;
	public final static int BI_SHIFTROUND = 1000+13;
	public final static int BI_EQUAL = 800+14;
	public final static int BI_NOTEQUAL = 800+15;
	public final static int BI_LESS = 900+16;
	public final static int BI_ELESS = 900+17;
	public final static int BI_GREATER = 900+18;
	public final static int BI_EGREATER = 900+19;
	public final static int BI_INSTANCEOF = 900+20;
	public final static int BI_MOD = 1200+21;
	public final static int BI_BITNOT = 1300+22;
	public final static int BI_LEFTINCREMENT = 1300+23;
	public final static int BI_RIGHTINCREMENT = 1300+24;
	public final static int BI_LEFTDECREMENT = 1300+25;
	public final static int BI_RIGHTDECREMENT = 1300+26;
	public final static int BI_TRUE = 1400+27;
	public final static int BI_FALSE = 1400+28;
	public final static int BI_NULL = 1400+29;
	public final static int BI_CAST = 1300+30;
	public final static int BI_INT = 1400+31;
	public final static int BI_FLOAT = 1400+32;
	public final static int BI_STRING = 1400+33;
	public final static int BI_CHAR = 1400+34;
	//public final static int BI_DESIGNATOR=1400+35;
	public final static int BI_NONE=36;
	public final static int BI_QUESTION=200+37;
	public final static int BI_SET=100+38;
	public final static int BI_SETADD=100+39;
	public final static int BI_SETSUB=100+40;
	public final static int BI_SETMUL=100+41;
	public final static int BI_SETDIV=100+42;
	public final static int BI_SETMODULO=100+43;
	public final static int BI_SETRSHIFT=100+44;
	public final static int BI_SETRROUNDSHIFT=100+45;
	public final static int BI_SETLSHIFT=100+46;
	public final static int BI_SETAND=100+47;
	public final static int BI_SETOR=100+48;
	public final static int BI_SETXOR=100+49;
	public final static int BI_VARIABLE=1400+50;
	public final static int BI_FIELD=1400+51;
	public final static int BI_CALL=1400+52;
	public final static int BI_ARRAY=1400+53;
	public final static int BI_CONSTRUCTOR=1400+54;
	public final static int BI_CONSTRUCTORARRAY=1400+55;
	public final static int BI_METHOD=1400+56;
    Vector data=new Vector();
    int type=0;
	public void read(Element xml, int version) {

		super.read(xml, version);
		type = UtiOB.readInteger(xml,"type", 0);
		UtiOB.readList(xml, "data", data, version, this);
	}
	public int getPrecedenceLevel()
	{
		return type / 100;
	}
	public void write(Element xml, int version) {

		super.write(xml, version);
		UtiOB.writeInteger(xml,"type", type);
		UtiOB.writeList(xml, "data", data, version);
	}
	public UtiAusdruck(BaseCode p) {
		super(p);

	}
	public UtiAusdruck(UtiAusdruck a, UtiAusdruck b, int build_in)
	{
		super(null);
		setType(a, b, build_in);
	}
	public UtiAusdruck(UtiAusdruck a, UtiAusdruck b, UtiAusdruck c, int build_in)
	{
		super(null);
		setType(a, b,c,  build_in);
	}
	public UtiAusdruck(UtiAusdruck a, int build_in)
	{
		super(null);
		setType(a, build_in);
   }
	public UtiAusdruck(int build_in)
	{
		super(null);
		setType( build_in);
	}	
	public UtiAusdruck(UtiType a, UtiAusdruck u)
	{
		super(null);
		setType(a, u);
	}
	public UtiAusdruck(Link a, UtiAusdruck u)
	{
		super(null);
		setType(a, u);
	}
	public UtiAusdruck(String str)
	{
		super(null);
		setType(str);
	}
	public UtiAusdruck(Integer str)
	{
		super(null);
		setType(str);
	}
	public UtiAusdruck(Character str)
	{
		super(null);
		setType(str);
	}
	public UtiAusdruck(Double str)
	{
		super(null);
		setType(str);
	}
	/*public UtiAusdruck(UtiDesignator d)
	{
		super(null);
		setType(d);
	}*/
	/*public void setType(UtiDesignator d)
	{
		data.clear();
	    data.addElement(d); d.setObjParent(this);
	    type = BI_DESIGNATOR;
	}*/
	public UtiOB getElement(int i)
	{
		return (UtiOB)data.elementAt(i);
	}
	public int getCount()
	{
		return data.size();
	}
    public void setType(UtiAusdruck a, UtiAusdruck b, int build_in) 
    {
       data.clear();
       data.addElement(a); a.setObjParent(this);
       data.addElement(b); b.setObjParent(this);
       type = build_in;
    }
    public void setType(UtiAusdruck a, UtiAusdruck b, UtiAusdruck c, int build_in) 
    {
       data.clear();
       data.addElement(a); a.setObjParent(this);
       data.addElement(b); b.setObjParent(this);
       data.addElement(c); b.setObjParent(this);
       type = build_in;
    }
    public void setType(UtiAusdruck a, int build_in) 
    {
       data.clear();
       data.addElement(a); a.setObjParent(this);
       type = build_in;
    }
    public void setType(int build_in) 
    {
       //data.clear();
       type = build_in;
    }
    public void addData(UtiOB a) {
    	data.addElement(a); a.setObjParent(this);
    }
    public void setType(Link a, UtiAusdruck u)
    {
       data.clear();
       data.addElement(a); //a.setObjParent(this);
       data.addElement(u); u.setObjParent(this);
       type = BI_CAST;
    }
    public void setType(UtiType a, UtiAusdruck u)
    {
       data.clear();
       data.addElement(new Link(a)); //a.setObjParent(this);
       data.addElement(u); u.setObjParent(this);
       type = BI_CAST;
    }
    public void setType(String str)
    {
    	data.clear();
        data.addElement(new UtiString(str));
        type = BI_STRING;
    	
    }
    public void setType(Integer str)
    {
    	data.clear();
        data.addElement(new UtiInteger(str.intValue()));
        type = BI_INT;    	
    }
    public void setType(Double str)
    {
    	data.clear();
        data.addElement(new UtiNumber(str.doubleValue()));
        type = BI_FLOAT;    	
    }
    public void setType(Character str)
    {
    	data.clear();
        data.addElement(new UtiCharacter(str.charValue()));
        type = BI_CHAR;    	
    }
	public int getType() {
		return type;
	}
	public void searchImports(ImportList list) {
		if (type == BI_STRING) {
			list.addSecondary((UtiType)UtiProgram.MainProg.getBase().getPackageByName("java").getPackageByName("lang").getChildByName("String"));
		}
		for (int i = 0; i < data.size(); i++) {
			if (data.elementAt(i) instanceof Link) {
				Object o = ((Link)data.elementAt(i)).getObject();
				if (o instanceof BaseType)
					list.addSecondary((BaseType)o);
			}
			if (data.elementAt(i) instanceof BaseCode) {
				((BaseCode)data.elementAt(i)).searchImports(list);
			}
			
		}
	       
	}
	public UtiType getReturnType() {
		
	
	switch (getType()) {
	case UtiAusdruck.BI_VARIABLE: {Link n = (Link)getElement(0);return ((UtiVariable)n.getObject()).getType();
	}
	case UtiAusdruck.BI_CALL: 
	case UtiAusdruck.BI_CONSTRUCTOR: {Link n = (Link)getElement(0);	return ((UtiMethod)n.getObject()).getResultType();
	}
	case UtiAusdruck.BI_CONSTRUCTORARRAY: {
		Link n = (Link)getElement(0);
		int dimensions = getCount()-1;
		UtiType typ = (UtiType)n.getObject();
		UtiType a2= UtiProgram.MainProg.getArrayType(typ, dimensions);
		return a2;
	}
	case UtiAusdruck.BI_ARRAY: {UtiAusdruck base = (UtiAusdruck)getElement(0);
		UtiArray ar = (UtiArray)base.getReturnType();return ar.getBasetype();
	}
	case UtiAusdruck.BI_METHOD: {UtiAusdruck base = (UtiAusdruck)getElement(0);
		Link n = (Link)getElement(1);return ((UtiMethod)n.getObject()).getResultType();
	}
	case UtiAusdruck.BI_FIELD: {UtiAusdruck base = (UtiAusdruck)getElement(0);
		Link n = (Link)getElement(1);return ((UtiVariable)n.getObject()).getType();
	}
	/*case UtiAusdruck.BI_PLUS: {
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
		;
		break;
	case UtiAusdruck.BI_LEFTDECREMENT: {

		genFAusdruck(a, "--");
	}
		;
		break;
	case UtiAusdruck.BI_RIGHTDECREMENT: {
		genBAusdruck(a, "--");

	}		CodeSys.o().print("(");
		generateType(((UtiType)((Link) a.getElement(0)).getObject()));
		CodeSys.o().print(")");
		genF2Ausdruck(a, "");
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
		break;*/
	case UtiAusdruck.BI_CAST: {	return ((UtiType)((Link) getElement(0)).getObject());}
	/*
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
		break;*/
	default: {
       return null;
	}

	}
	}
}
