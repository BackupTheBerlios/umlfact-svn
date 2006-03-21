package uti.codeModel;

import org.w3c.dom.Element;

import uti.java.UtiOB;

public class UtiFor extends UtiCommand {
	UtiBlock block=new UtiBlock(this);
	UtiBlock init = new UtiBlock(this);
	UtiAusdruck bedingung = new UtiAusdruck(this);
	UtiBlock step = new UtiBlock(this);
	public UtiFor(BaseCode p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	public UtiBlock getBlock() {
		return block;
	}
	public void setBlock(UtiBlock code) {
		code.setObjParent(this);
		this.block = code;
	}
	public UtiAusdruck getBedingung() {
		return bedingung;
	}
	public void setBedingung(UtiAusdruck bedingung) {
		bedingung.setObjParent(this);
		this.bedingung = bedingung;
	}
	public UtiBlock getInit() {
		return init;
	}
	public void setInit(UtiBlock init) {
		init.setObjParent(this);
		this.init = init;
	}
	public UtiBlock getStep() {
		return step;
	}
	public void setStep(UtiBlock step) {
		step.setObjParent(this);
		this.step = step;
	}
	public void read(Element xml, int version) {
		// TODO Auto-generated method stub
		super.read(xml, version);
		UtiOB.readObject(xml, "block", block, version);
		UtiOB.readObject(xml, "init", init, version);
		UtiOB.readObject(xml, "step", step, version);
		UtiOB.readObject(xml, "bedingung", bedingung, version);
	}

	public void write(Element xml, int version) {
		// TODO Auto-generated method stub
		super.write(xml, version);
		UtiOB.writeObject(xml, "block", block, version);
		UtiOB.writeObject(xml, "init", init, version);
		UtiOB.writeObject(xml, "step", step, version);
		UtiOB.writeObject(xml, "bedingung", bedingung, version);
	}
}
