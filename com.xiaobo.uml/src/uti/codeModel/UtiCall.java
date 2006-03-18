package uti.codeModel;

import java.util.Vector;

import org.w3c.dom.Element;

import uti.java.Link;
import uti.java.UtiOB;

public class UtiCall extends UtiCommand {
    Link method = new Link();
    Vector parameter = new Vector();
	public UtiCall(BaseCode p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	
	public void setMethod(UtiMethod m)
	{
		method.setObject(m);
		int s = m.parameter.size();
		
	}
	public UtiAusdruck getAusdruck(int i)
	{
		return (UtiAusdruck)parameter.elementAt(i);
	}
	public UtiMethod getMethod()
	{
		return (UtiMethod)method.getObject();
	}

	public void read(Element xml, int version) {
		// TODO Auto-generated method stub
		super.read(xml, version);
		UtiOB.readObject(xml, "method", method, version);
		UtiOB.readList(xml, "parameter", parameter, version, this);
	}

	public void write(Element xml, int version) {
		// TODO Auto-generated method stub
		super.write(xml, version);
		UtiOB.writeObject(xml, "method", method, version);
		UtiOB.writeList(xml, "parameter", parameter, version);
	}
   
}
