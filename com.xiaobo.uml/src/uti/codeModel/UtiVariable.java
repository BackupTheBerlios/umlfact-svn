package uti.codeModel;

import org.w3c.dom.Element;

import uti.java.*;

public class UtiVariable extends UtiName implements BaseCommand{
    Link type = new Link();
    boolean utifinal = false;
    boolean utistatic = false;
    public void setType(UtiType t)
    {
    	type.setObject(t);
    }
    public UtiType getType()
    {
    	return (UtiType)type.getObject();
    }
    // TODO Variableninitialisierung
	public UtiVariable(BaseCode p) {
		super(p);
		
	}
	public boolean isStatic() {
		return utistatic;
	}
	public void setStatic(boolean utistatic) {
		this.utistatic = utistatic;
	}
	public boolean isFinal() {
		return utifinal;
	}
	public void setFinal(boolean utifinal) {
		this.utifinal = utifinal;
	}
	public void read(Element xml, int version) {
		// TODO Auto-generated method stub
		super.read(xml, version);
		UtiOB.readObject(xml, "type", type, version);
		setStatic(UtiOB.readBoolean(xml, "static"));
		setFinal(UtiOB.readBoolean(xml, "final"));
	}
	public void write(Element xml, int version) {
		// TODO Auto-generated method stub
		super.write(xml, version);
		UtiOB.writeObject(xml, "type", type, version);
		UtiOB.writeBoolean(xml, "static", isStatic());
		UtiOB.writeBoolean(xml, "final", isFinal());
	}

}
