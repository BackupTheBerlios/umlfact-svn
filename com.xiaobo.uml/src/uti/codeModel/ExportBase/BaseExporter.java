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
public String getPackageDir(UtiPackage pack) {
	if (pack == null) return "";
	String dir = getPackageDir((UtiPackage)pack.getObjParent());
	return dir + pack.getName()+"/";
}
}
