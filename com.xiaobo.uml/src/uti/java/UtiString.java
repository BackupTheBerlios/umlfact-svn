package uti.java;

import org.w3c.dom.Element;

public class UtiString extends UtiOB {
    String value;
	public UtiString() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UtiString(UtiOB p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
    public UtiString(String d)
    {
    	value = d;
    }
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public void read(Element xml, int version) {
		// TODO Auto-generated method stub
		super.read(xml, version);
		value = UtiOB.readString(xml, "value");
	}
	public void write(Element xml, int version) {
		// TODO Auto-generated method stub
		super.write(xml, version);
		UtiOB.writeString(xml, "value", value);
	}
}
