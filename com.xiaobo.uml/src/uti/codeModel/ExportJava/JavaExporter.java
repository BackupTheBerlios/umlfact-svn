package uti.codeModel.ExportJava;

import uti.codeModel.BaseCode;
import uti.codeModel.UtiClass;
import uti.codeModel.UtiInterface;
import uti.codeModel.UtiPackage;
import uti.codeModel.ExportBase.BaseExporter;
import uti.codeModel.ExportBase.CodeSys;

public class JavaExporter extends BaseExporter {

	public JavaExporter() {
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
		return dir + pack.getName().toUpperCase()+".";
	}
	public void generateClass(UtiClass cl)
	{
		CodeSys.output(cl.getName()+".java");
		UtiPackage p = (UtiPackage)cl.getObjParent();
		String def = getPackageDefine(p)+cl.getName().toUpperCase();
		CodeSys.o().println("package "+def);
		CodeSys.o().println();
		CodeSys.o().println("public class "+cl.getName()+ " {");
		
		
		CodeSys.o().println("}");
		CodeSys.o().println();
		

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
