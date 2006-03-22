package uti.java;

import org.w3c.dom.Element;

public class UtiCharacter extends UtiOB  {
    char value;
    public UtiCharacter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UtiCharacter(UtiOB p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	public UtiCharacter(char v)
	{
		value =v;
	}

	public char getValue() {
		return value;
	}

	public void setValue(char value) {
		this.value = value;
	}

	public void read(Element xml, int version) {
		// TODO Auto-generated method stub
		super.read(xml, version);
		value = (char)UtiOB.readInteger(xml, "value");
	}

	public void write(Element xml, int version) {
		// TODO Auto-generated method stub
		super.write(xml, version);
		UtiOB.writeInteger(xml, "value", value);
	}

}
