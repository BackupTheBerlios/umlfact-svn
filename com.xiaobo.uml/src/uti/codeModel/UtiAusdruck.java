package uti.codeModel;

import java.util.*;

import org.w3c.dom.Element;

import uti.java.*;

public class UtiAusdruck extends BaseCode {	
	public final static int BI_PLUS = 0;
	public final static int BI_MINUS = 1;
	public final static int BI_MULTIPLY = 2;
	public final static int BI_DIVIDE = 3;
	public final static int BI_NEG = 4;
	public final static int BI_OR = 5;
	public final static int BI_LOGICOR = 6;
	public final static int BI_LOGICAND = 7;
	public final static int BI_AND = 8;
	public final static int BI_NOT = 9;
	public final static int BI_XOR = 10;
	public final static int BI_LSHIFT = 11;
	public final static int BI_RSHIFT = 12;
	public final static int BI_SHIFTROUND = 13;
	public final static int BI_EQUAL = 14;
	public final static int BI_NOTEQUAL = 15;
	public final static int BI_LESS = 16;
	public final static int BI_ELESS = 17;
	public final static int BI_GREATER = 18;
	public final static int BI_EGREATER = 19;
	public final static int BI_INSTANCEOF = 20;
	public final static int BI_MOD = 21;
	public final static int BI_BITNOT = 22;
	public final static int BI_LEFTINCREMENT = 23;
	public final static int BI_RIGHTINCREMENT = 24;
	public final static int BI_LEFTDECREMENT = 25;
	public final static int BI_RIGHTDECREMENT = 26;
	public final static int BI_TRUE = 27;
	public final static int BI_FALSE = 28;
	public final static int BI_NULL = 29;
	public final static int BI_CAST = 30;
	public final static int BI_INT = 31;
	public final static int BI_FLOAT = 32;
	public final static int BI_STRING = 33;
	public final static int BI_CHAR = 34;
	public final static int BI_DESIGNATOR=35;
	public final static int BI_NONE=36;
    Vector data=new Vector();
    int type=0;
	public void read(Element xml, int version) {
		// TODO Auto-generated method stub
		super.read(xml, version);
		type = UtiOB.readInteger(xml,"type");
		UtiOB.readList(xml, "data", data, version, this);
	}
	public void write(Element xml, int version) {
		// TODO Auto-generated method stub
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
	public UtiAusdruck(TypeDescription a, UtiAusdruck u)
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
	public UtiAusdruck(UtiDesignator d)
	{
		super(null);
		setType(d);
	}
	public void setType(UtiDesignator d)
	{
		data.clear();
	    data.addElement(d); d.setObjParent(this);
	    type = BI_DESIGNATOR;
	}
    public void setType(UtiAusdruck a, UtiAusdruck b, int build_in) 
    {
       data.clear();
       data.addElement(a); a.setObjParent(this);
       data.addElement(b); b.setObjParent(this);
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
       data.clear();
       type = build_in;
    }
    public void setType(TypeDescription a, UtiAusdruck u)
    {
       data.clear();
       data.addElement(a); a.setObjParent(this);
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
}
