package uti.codeModel;

public class UtiDesField extends UtiDesStep {
    UtiVariable field;
	public UtiDesField(BaseCode p, UtiVariable v) {
		super(p);
		setField(v);
		
	}
	public UtiVariable getField() {
		return field;
	}
	public void setField(UtiVariable field) {
		this.field = field;
		this.result = field.getType();
	}

}
