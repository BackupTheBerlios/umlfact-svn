package uti.codeModel;

import uti.java.Link;

public class UtiDesTypeConvert extends UtiDesStep {
    Link type=new Link();
	public UtiDesTypeConvert(BaseCode p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	public UtiType getType() {
		return (UtiType)type.getObject();
	}
	public void setType(UtiType type) {
		this.type.setObject(type);
	}

}