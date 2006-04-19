package uti.codeModel;

import org.w3c.dom.Element;

import uti.java.*;

public class UtiVariable extends UtiName implements BaseCommand{
    Link type= new Link();
    boolean utifinal = false;
    boolean utistatic = false;
   
    // TODO Variableninitialisierung
	public UtiVariable(BaseCode p) {
		super(p);		
	}
	/*public void setDescription(TypeDescription d)
	{
		des = d;		
	}
	public TypeDescription getDescription()
	{
		return des;
	}*/
	public Link intern_type()
	{
		return type;
	}
	public UtiType getType()
	{
		return (UtiType)type.getObject();
	}
	public void setType(UtiType t)
	{
		type.setObject(t);
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
		UtiOB.readObject(xml, "type",type, version);
		setStatic(UtiOB.readBoolean(xml, "static", false));
		setFinal(UtiOB.readBoolean(xml, "final", false));
		
	}
	public void write(Element xml, int version) {
		// TODO Auto-generated method stub
		super.write(xml, version);
		UtiOB.writeObject(xml, "type", type, version);
		UtiOB.writeBoolean(xml, "static", isStatic());
		UtiOB.writeBoolean(xml, "final", isFinal());
		
	}
	public void searchImports(ImportList list){
		Object o = type.getObject();
		//((BaseCode)o).searchImports(list);
		if (o instanceof UtiType)
			list.addSecondary((UtiType)o);
		
	}

}
