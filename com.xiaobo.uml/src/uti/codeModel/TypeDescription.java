package uti.codeModel;

import org.w3c.dom.Element;

import uti.java.Link;
import uti.java.UtiOB;

/*public class TypeDescription extends BaseCode {
	Link type = new Link();
	int arraysize=0;
	public TypeDescription() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TypeDescription(UtiOB p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	
	public Link intern_type()
	{
		return type;
	}
	public void addArray(int i)
	{
		arraysize += i;
	}
	public int getArraySize()
	{
		return arraysize;
	}
	public void setType(UtiType t)
    {
    	type.setObject(t);
    }
    public UtiType getType()
    {
    	return (UtiType)type.getObject();
    }
    public void read(Element xml, int version) {
		// TODO Auto-generated method stub
		super.read(xml, version);
		UtiOB.readObject(xml, "type", type, version);
		
		arraysize = UtiOB.readInteger(xml, "arraysize");
	}
	public void write(Element xml, int version) {
		// TODO Auto-generated method stub
		super.write(xml, version);
		UtiOB.writeObject(xml, "type", type, version);
		UtiOB.writeInteger(xml, "arraysize", arraysize);
	}

}*/
