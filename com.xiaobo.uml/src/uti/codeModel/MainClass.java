package uti.codeModel;

import uti.codeModel.ExportBase.BaseExporter;
import uti.codeModel.ExportBase.CodeSys;
import uti.codeModel.ExportCPP.*;
import uti.codeModel.ExportJava.*;

public class MainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
        UtiPackage base = new UtiPackage(null);
        base.setName("Package");
        System.out.println("CodeModel");
        UtiClass obj = base.addObject("MainClass");
        base.saveToFile("Hamster.xml");
        BaseExporter exp = new CPPExporter();
        exp.export(base);
        CodeSys.free();
	}

}
