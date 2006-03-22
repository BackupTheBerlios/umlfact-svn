package uti.java;

import org.w3c.dom.Element;

public class UtiBoolean extends UtiOB {
    boolean value;
	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	public UtiBoolean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UtiBoolean(UtiOB p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	public UtiBoolean(boolean b) {
		value = b;
	}

	public void read(Element xml, int version) {
		// TODO Auto-generated method stub
		super.read(xml, version);
	value =	UtiOB.readBoolean(xml, "value");
	}

	public void write(Element xml, int version) {
		// TODO Auto-generated method stub
		super.write(xml, version);
		UtiOB.writeBoolean(xml, "value", value);
	}

}
