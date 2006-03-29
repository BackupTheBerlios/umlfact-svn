package uti.codeModel;

import org.w3c.dom.Element;
import uti.java.*;

public class UtiName extends BaseCode implements BaseName {
	String sname="Unnamed";
	public UtiName(BaseCode p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	/* (non-Javadoc)
	 * @see codeModel.BaseName#setName(java.lang.String)
	 */
	public void setName(String name)
	   {
		   if (sname.equals(name)) return;
		   sname = name;
		   BaseCollection p = (BaseCollection)Parent;
		   if (p == null) {
		       sname = name;
		   } else {
			   sname = name;
			   p.renameChild(this, name);		   
		   }
	   }
	   /* (non-Javadoc)
	 * @see codeModel.BaseName#getName()
	 */
	public String getName()
	   {
		   return sname;
	   }
	public void read(Element xml, int version) {
		// TODO Auto-generated method stub
		super.read(xml, version);
		setName(UtiOB.readString(xml, "name"));
	}
	public void write(Element xml, int version) {
		// TODO Auto-generated method stub
		super.write(xml, version);
		UtiOB.writeString(xml, "name",getName());
	}
}
