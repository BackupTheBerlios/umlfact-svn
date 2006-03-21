package uti.codeModel;

import java.util.*;

public class UtiDesignator extends UtiCommand {
    Vector steps = new Vector();
    UtiVariable BaseVar = null;
	public UtiDesignator(BaseCode p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	void addFieldAccess(UtiVariable var) {
		UtiDesField f = new UtiDesField(this, var);
		steps.addElement(f);
	}
	void addMethodAccess(UtiMethod var) {
		UtiDesMethod f = new UtiDesMethod(this, var);
		steps.addElement(f);
	}
	void addArrayAccess(UtiAusdruck[] exp) {
		UtiDesArray f = new UtiDesArray(this, exp);
		steps.addElement(f);
	}

}
