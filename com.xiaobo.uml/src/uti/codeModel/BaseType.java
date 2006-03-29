package uti.codeModel;

import org.w3c.dom.Element;
import uti.java.*;

public class BaseType extends UtiType {
	public static final int BT_BOOL = 0;
	public static final int BT_CHAR = 1;
	public static final int BT_BYTE = 2;
	public static final int BT_SHORT = 3;
	public static final int BT_INT = 4;
	public static final int BT_LONG = 5;
	public static final int BT_FLOAT = 6;
	public static final int BT_DOUBLE = 7;
    
    int type;
    
    public static BaseType bt_bool = new BaseType(BT_BOOL, "boolean", BT_BOOL+200);
    public static BaseType bt_char = new BaseType(BT_CHAR, "char", BT_CHAR+200);
    public static BaseType bt_byte = new BaseType(BT_BYTE, "byte", BT_BYTE+200);
    public static BaseType bt_short = new BaseType(BT_SHORT, "short", BT_SHORT+200);
    public static BaseType bt_int = new BaseType(BT_INT, "int", BT_INT+200);
    public static BaseType bt_long = new BaseType(BT_LONG, "long", BT_LONG+200);
    public static BaseType bt_float = new BaseType(BT_FLOAT, "float", BT_FLOAT+200);
    public static BaseType bt_double = new BaseType(BT_DOUBLE, "double", BT_DOUBLE+200);
	public BaseType(BaseCode p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	public BaseType(int type, String name, int id) {
		super(null);
		setType(type);
		setName(name);
		setID(id);
		UtiOB.addStaticRef(this);
	}

	public String getName() {
		// TODO Auto-generated method stub
		return super.getName();
	}

	public void read(Element xml, int version) {
		// TODO Auto-generated method stub
		super.read(xml, version);
		type = UtiOB.readInteger(xml, "type");
	}

	public void setName(String name) {
		// TODO Auto-generated method stub
		sname = name;
	}

	public void write(Element xml, int version) {
		// TODO Auto-generated method stub
		super.write(xml, version);
		UtiOB.writeInteger(xml, "type", type);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
