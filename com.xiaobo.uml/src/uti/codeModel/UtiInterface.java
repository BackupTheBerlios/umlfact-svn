package uti.codeModel;

public class UtiInterface extends UtiCollection {
	public UtiInterface(BaseCode p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	UtiMethod addMethod(String Name)
	{
		UtiMethod m = new UtiMethod(this);
		m.setName(Name);
		addChild(m);
		return m;
	}
}
