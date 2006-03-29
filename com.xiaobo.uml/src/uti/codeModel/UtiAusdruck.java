package uti.codeModel;

import java.util.*;

import org.w3c.dom.Element;

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
//	public final static int BI_FIELD=1400+51;
	public final static int BI_CALL=1400+52;
	public final static int BI_ARRAY=1400+53;
    Vector data=new Vector();
    int type=0;
	public void read(Element xml, int version) {

		super.read(xml, version);
		type = UtiOB.readInteger(xml,"type");
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
