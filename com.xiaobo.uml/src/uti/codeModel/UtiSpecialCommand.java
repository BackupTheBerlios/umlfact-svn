package uti.codeModel;

import org.w3c.dom.Element;
import uti.java.*;

public class UtiSpecialCommand extends UtiCommand {
    public final static int RETURN=0;
    public final static int BREAK=1;
    public final static int CONTINUE=2;
	UtiAusdruck ausdruck=null;
    int type=RETURN;
	public UtiSpecialCommand(BaseCode p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	public void read(Element xml, int version) {
		// TODO Auto-generated method stub
		super.read(xml, version);
		ausdruck = (UtiAusdruck)UtiOB.readObjectMulti(xml, "ausdruck", version, this);
	}
	public void write(Element xml, int version) {
		// TODO Auto-generated method stub
		super.write(xml, version);
		UtiOB.writeObject(xml, "ausdruck", ausdruck,version);
	}
	public UtiAusdruck getAusdruck() {
		return ausdruck;
	}
	public void setAusdruck(UtiAusdruck ausdruck) {
		this.ausdruck = ausdruck;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

}
