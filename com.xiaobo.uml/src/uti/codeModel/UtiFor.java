package uti.codeModel;

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
		this.block = code;
	}
	public UtiAusdruck getBedingung() {
		return bedingung;
	}
	public void setBedingung(UtiAusdruck bedingung) {
		this.bedingung = bedingung;
	}
	public UtiBlock getInit() {
		return init;
	}
	public void setInit(UtiBlock init) {
		this.init = init;
	}
	public UtiBlock getStep() {
		return step;
	}
	public void setStep(UtiBlock step) {
		this.step = step;
	}
}
