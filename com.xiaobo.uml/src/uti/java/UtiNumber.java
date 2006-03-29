package uti.java;

import org.w3c.dom.Element;

public class UtiNumber extends UtiOB {
    double value;
	public UtiNumber() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UtiNumber(UtiOB p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
    public UtiNumber(double d)
    {
    	value = d;
    }
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public void read(Element xml, int version) {
		// TODO Auto-generated method stub
		super.read(xml, version);
		value = UtiOB.readDouble(xml, "value");
	}

	public void write(Element xml, int version) {
		// TODO Auto-generated method stub
		super.write(xml, version);
		UtiOB.writeDouble(xml, "value", value);
	}
	public String toString() {
		// TODO Auto-generated method stub
		return Double.toString(value);
	}

}
