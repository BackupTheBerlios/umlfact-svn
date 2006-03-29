package uti.codeModel;

import org.w3c.dom.Element;

import uti.java.Link;
import uti.java.UtiOB;
import java.util.*;
/** Veraltet nicht benutzen*/
public class UtiDesMethod extends UtiDesStep {
    Link method=new Link();
    Vector params = new Vector();
	public UtiDesMethod(BaseCode p, UtiMethod m, Vector parameter) {
		super(p);
		params = parameter;
		setMethod(m);
	}
	public UtiMethod getMethod() {
		return (UtiMethod)method.getObject();
	}
	public void setMethod(UtiMethod method) {
		this.method.setObject(method);
		//setResult(method.getResultType());
	}
	public void read(Element xml, int version) {
		// TODO Auto-generated method stub
		super.read(xml, version);
		UtiOB.readObject(xml, "method", method, version);
		UtiOB.readList(xml, "parameter", params, version, this);
	}
	public void write(Element xml, int version) {
		// TODO Auto-generated method stub
		super.write(xml, version);
		UtiOB.writeObject(xml, "method", method, version);
		UtiOB.writeList(xml, "parameter", params, version);
	}

}
