package uti.codeModel;

public class UtiCall extends UtiCommand {
    UtiMethod method;
    UtiAusdruck parameter[];
	public UtiCall(BaseCode p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	
	public void setMethod(UtiMethod m)
	{
		method = m;
		int s = method.parameter.size();
		parameter = new UtiAusdruck[s];
		for (int i = 0; i < parameter.length; i++) {
			parameter[i] = new UtiAusdruck(this);
		}
	}
	public UtiAusdruck getAusdruck(int i)
	{
		return parameter[i];
	}
	public UtiMethod getMethod()
	{
		return method;
	}
   
}
