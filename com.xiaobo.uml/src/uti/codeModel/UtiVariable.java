package uti.codeModel;

public class UtiVariable extends UtiName implements BaseCommand{
    UtiType type;
    boolean utifinal = false;
    boolean utistatic = false;
    public void setType(UtiType t)
    {
    	type = t;
    }
    public UtiType getType()
    {
    	return type;
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

}
