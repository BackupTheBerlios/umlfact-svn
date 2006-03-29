package uti.codeModel.ExportBase;

import uti.codeModel.*;
import uti.java.UtiOB;

public class BaseExporter extends UtiOB{
   public BaseExporter() {
	   
   }
   public void export(UtiPackage pack)
   {
	   
   }
   public String createPreview(BaseCode obj)
   {
	   return "NotImplementet";
   }
   public void generateMethod(UtiMethod m) {
	   
   }
   public void generateVariable(UtiVariable var)
   {
	   
   }
   public void generateType(TypeDescription desc)
   {
	   
   }
   public void generateAusdruck(UtiAusdruck a)
   {
	   
   }
   public void generateIf(UtiIf ifobj)
   {
	   
   }
   public void generateWhile(UtiWhile w)
   {
	   
   }
   public void generateExtern(UtiExtern w)
   {
	   
   }
   public void generateSpecialCommand(UtiSpecialCommand w)
   {
	   
   }
   public void generateBlock(UtiBlock b)
   {
	   for (int i = 0; i< b.getCommandCount(); i++) {
		   Object o = b.getCommand(i);
		   if (o instanceof UtiVariable) {
			   generateVariable((UtiVariable)o);
		   } else if (o instanceof UtiAusdruck) {
			   generateAusdruck((UtiAusdruck)o);
			   CodeSys.o().println(";");
		   } else if (o instanceof UtiBlock) {
			   generateBlock((UtiBlock)o);
		   } else if (o instanceof UtiIf) {
			   generateIf((UtiIf)o);
		   } else if (o instanceof UtiWhile) {
			   generateWhile((UtiWhile)o);
		   } else if (o instanceof UtiExtern) {
			   generateExtern((UtiExtern)o);
		   } else if (o instanceof UtiSpecialCommand) {
			   generateSpecialCommand((UtiSpecialCommand)o);
		   }
		   //CodeSys.o().println("");
	   }
   }
   public void generateClass(UtiClass cl) {
	   for (int i = 0; i < cl.getChildCount(); i++) {
		   Object o = cl.getChild(i);
		   if (o instanceof UtiMethod) {
			   generateMethod((UtiMethod)o);
		   } else if (o instanceof UtiVariable) {
			   generateVariable((UtiVariable)o);
		   } else if (o instanceof UtiExtern) {
			   generateExtern((UtiExtern)o);
		   }
	   }
   }
public String getPackageDir(UtiPackage pack) {
	if (pack == null) return "";
	String dir = getPackageDir((UtiPackage)pack.getObjParent());
	return dir + pack.getName()+"/";
}
}
