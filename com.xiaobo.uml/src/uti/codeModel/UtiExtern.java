package uti.codeModel;

import org.w3c.dom.Element;
import uti.java.*;

public class UtiExtern extends UtiCommand implements BaseName {
    String type;
    String code;
	public void read(Element xml, int version) {
		// TODO Auto-generated method stub
		super.read(xml, version);
		type = UtiOB.readString(xml, "type");
		code = UtiOB.readString(xml, "code");
	}

	public void write(Element xml, int version) {
		// TODO Auto-generated method stub
		super.write(xml, version);
		UtiOB.writeString(xml, "type", type);
		UtiOB.writeString(xml, "code", code);
	}

	public UtiExtern() {
		super(null);
		// TODO Auto-generated constructor stub
	}

	public void setName(String name) {
		// TODO Auto-generated method stub

	}

	public String getName() {
		// TODO Auto-generated method stub
		return "";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
