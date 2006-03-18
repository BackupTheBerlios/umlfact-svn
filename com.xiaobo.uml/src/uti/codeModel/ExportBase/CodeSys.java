package uti.codeModel.ExportBase;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;

public class CodeSys {
	static Stack css_stack = new Stack();
	static HashMap out_hash = new HashMap();
	static final int USECASE = 0;
	static final int IGNORECASE = 1;
	protected static CodeSysStatus css = new CodeSysStatus(); 
	public static String outputFilePath="";
	public static void setCaseMode(int i)
	{
		css.casemode = i;
	}
	public static int getCaseMode()
	{
		return css.casemode;
	}
	static CodeOutput getOutput(String n)
	{
		String name = outputFilePath+n;
		CodeOutput co = (CodeOutput)out_hash.get(name);
		if (co != null) return co;
		try {
		    co = new CodeOutput(name);
		    out_hash.put(name, co);
		} catch (IOException ex) {
			return null;
		}
		return co;
	}
	public static void free()
	{
		Object a[] = out_hash.values().toArray();
		for (int i = 0; i < a.length; i++) {
			((CodeOutput)a[i]).flush();
			((CodeOutput)a[i]).close();
		}
	}
	public static CodeOutput o()
	{
		if (css.coutput == null) {
			return null;
		} else {
		   return css.coutput;
		}
	}
	public static void setOutputDir(String d)
	{
		outputFilePath = d;
		String str = outputFilePath;
		if (str.endsWith("/")) {
			str = str.substring(0, str.length()-1);
		}
		File f = new File(str);
		f.mkdirs();
		
	}
	public static void output(String n)
	{
		css.coutput = getOutput(n);
	}
	public static boolean compareCase(String s1, String s2)
	{
		if (css.casemode == USECASE) {
			return s1.equals(s2);
		} else {
			return s1.equalsIgnoreCase(s2);
		}
	}
	public static void pushEnv()
	{
	   css_stack.push(css);
	   css = new CodeSysStatus(css);
	}
	public static void popEnv()
	{
		if (css_stack.size() != 0) {
			css.free();
			css = (CodeSysStatus)css_stack.pop();
		}
	}   
}