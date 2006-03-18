package uti.codeModel;

public class UtiDesMethod extends UtiDesStep {
    UtiMethod method=null;
	public UtiDesMethod(BaseCode p, UtiMethod m) {
		super(p);
		setMethod(m);
	}
	public UtiMethod getMethod() {
		return method;
	}
	public void setMethod(UtiMethod method) {
		this.method = method;
		result = method.getResultType();
	}

}
