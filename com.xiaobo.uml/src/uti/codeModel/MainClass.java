package uti.codeModel;

import java.io.File;

import uti.codeModel.ExportBase.BaseExporter;
import uti.codeModel.ExportBase.CodeSys;
import uti.codeModel.ExportCPP.CPPExporter;
import uti.parser.*;


public class MainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
        UtiPackage base = new UtiPackage(null);
        base.setName("Package");
        System.out.println("CodeModel");
        File f = new File("/home/staud/test.java");
        ModelGenerator.readSingleFile(f, base);
        try {
        LinkMemory.doLink();
        } catch (SyntaxException e) {
        	System.out.println(e.toString());
        }
        //UtiClass obj = base.addObject("MainClass");
        base.saveToFile("Hamster.xml");
        BaseExporter exp = new CPPExporter();
        exp.export(base);
        CodeSys.free();
	}

}
