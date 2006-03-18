package uti.codeModel.ExportBase;

public class CodeSysStatus {
	public int casemode = CodeSys.USECASE;
	public CodeOutput coutput = null;
	CodeSysStatus()
	{
		
	}
	CodeSysStatus(CodeSysStatus old)
	{
		casemode = old.casemode;
		coutput = old.coutput;
	}
	public void free()
	{
		
	}
}
