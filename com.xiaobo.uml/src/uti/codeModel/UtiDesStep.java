package uti.codeModel;

import org.w3c.dom.Element;

import uti.java.Link;
import uti.java.UtiOB;

public class UtiDesStep extends BaseCode {
    Link result=new Link();
    UtiType getResult()
    {
    	return (UtiType)result.getObject();
    }
    void setResult(UtiType t) 
    {
    	result.setObject(t);
    }
	public UtiDesStep(BaseCode p) {
		super(p);
		
	}
	public void read(Element xml, int version) {
		// TODO Auto-generated method stub
		super.read(xml, version);
		UtiOB.readObject(xml, "result", result, version);
	}
	public void write(Element xml, int version) {
		// TODO Auto-generated method stub
		super.write(xml, version);
		UtiOB.writeObject(xml, "result", result, version);
	}

	
}
