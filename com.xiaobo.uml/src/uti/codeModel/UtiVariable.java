package uti.codeModel;

import org.w3c.dom.Element;

import uti.java.*;

public class UtiVariable extends UtiName implements BaseCommand{
    TypeDescription des= new TypeDescription();
    boolean utifinal = false;
    boolean utistatic = false;
   
    // TODO Variableninitialisierung
	public UtiVariable(BaseCode p) {
		super(p);		
	}
	public void setDescription(TypeDescription d)
	{
		des = d;		
	}
	public TypeDescription getDescription()
	{
		return des;
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
		UtiOB.readObject(xml, "type", des, version);
		setStatic(UtiOB.readBoolean(xml, "static"));
		setFinal(UtiOB.readBoolean(xml, "final"));
		
	}
	public void write(Element xml, int version) {
		// TODO Auto-generated method stub
		super.write(xml, version);
		UtiOB.writeObject(xml, "type", des, version);
		UtiOB.writeBoolean(xml, "static", isStatic());
		UtiOB.writeBoolean(xml, "final", isFinal());
		
	}

}
