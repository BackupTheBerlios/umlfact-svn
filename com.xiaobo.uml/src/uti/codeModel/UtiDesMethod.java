package uti.codeModel;

import org.w3c.dom.Element;

import uti.java.Link;
import uti.java.UtiOB;

public class UtiDesMethod extends UtiDesStep {
    Link method=new Link();
	public UtiDesMethod(BaseCode p, UtiMethod m) {
		super(p);
		setMethod(m);
	}
	public UtiMethod getMethod() {
		return (UtiMethod)method.getObject();
	}
	public void setMethod(UtiMethod method) {
		this.method.setObject(method);
		setResult(method.getResultType());
	}
	public void read(Element xml, int version) {
		// TODO Auto-generated method stub
		super.read(xml, version);
		UtiOB.readObject(xml, "method", method, version);
	}
	public void write(Element xml, int version) {
		// TODO Auto-generated method stub
		super.write(xml, version);
		UtiOB.writeObject(xml, "method", method, version);
	}

}
