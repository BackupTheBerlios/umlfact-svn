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
		
		UtiProgram prog = new UtiProgram();
		prog.readFile("/home/staud/test.java");
		prog.readFile("/home/staud/test2.java");
		prog.readFile("/home/staud/BaseClasses.java");
		prog.link();
		prog.saveToFile("test.xml");
	    prog.exportToCpp();
        
	}

}
