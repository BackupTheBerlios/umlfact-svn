package uti.java;

import org.w3c.dom.Element;

public class UtiInteger extends UtiOB {
    int value;
	public UtiInteger() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UtiInteger(UtiOB p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	public UtiInteger(int v)
	{
		value = v;
	}

	public void read(Element xml, int version) {
		// TODO Auto-generated method stub
		super.read(xml, version);
		value = UtiOB.readInteger(xml, "value");
	}

	public void write(Element xml, int version) {
		// TODO Auto-generated method stub
		super.write(xml, version);
		UtiOB.writeInteger(xml, "value", value);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
