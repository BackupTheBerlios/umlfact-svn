package uti.codeModel.ExportCPP;

import uti.codeModel.*;
import uti.codeModel.UtiPackage;
import uti.codeModel.ExportBase.BaseExporter;
import uti.codeModel.ExportBase.CodeSys;

public class CPPExporter extends BaseExporter {

	public CPPExporter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String createPreview(BaseCode obj) {
		// TODO Auto-generated method stub
		return super.createPreview(obj);
	}
	public String getPackageDefine(UtiPackage pack)
	{
		if (pack == null) return "";
		String dir = getPackageDefine((UtiPackage)pack.getObjParent());
		return dir + pack.getName().toUpperCase()+"_";
	}
	public int generateNameSpaces(UtiPackage pack)
	{
		if (pack == null) return 0;
		int v = generateNameSpaces((UtiPackage)pack.getObjParent());
		CodeSys.o().println("namespace "+pack.getName()+ "{");
		return v+1;
	}
	public void generateClass(UtiClass cl)
	{
		CodeSys.output(cl.getName()+".h");
		UtiPackage p = (UtiPackage)cl.getObjParent();
		String def = getPackageDefine(p)+cl.getName().toUpperCase();
		CodeSys.o().println("#ifndef "+def);
		CodeSys.o().println("#define "+def);
		CodeSys.o().println();
		int level = generateNameSpaces(p);
		CodeSys.o().println();
		CodeSys.o().println("class "+cl.getName()+ " {");
		
		
		CodeSys.o().println("}");
		CodeSys.o().println();
		for (int i = 0; i < level; i++) {
			CodeSys.o().println("}");
		}
		CodeSys.o().println();
		CodeSys.o().println("#endif");
	}
	public void generateInterface(UtiInterface in)
	{
		
	}
	public void generatePackage(UtiPackage pack)
    {
    	for (int i = 0; i < pack.getPackageCount(); i++) {
    		UtiPackage p = pack.getPackage(i);
    		generatePackage(p);
    	}
    	CodeSys.setOutputDir(getPackageDir(pack));
    	for (int i = 0; i < pack.getChildCount(); i++) {
    		if (pack.getChild(i) instanceof UtiClass) {
    			generateClass((UtiClass)pack.getChild(i));
    		}
    		if (pack.getChild(i) instanceof UtiInterface) {
    			generateInterface((UtiInterface)pack.getChild(i));
    		}
    	}
    	
    }
	public void export(UtiPackage pack) {
		// TODO Auto-generated method stub
		generatePackage(pack);
	}

}
