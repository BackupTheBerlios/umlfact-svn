package uti.codeModel;

public class UtiWhile extends UtiCommand {
	UtiAusdruck expression = new UtiAusdruck(this);
	UtiBlock block=new UtiBlock(this);
	boolean dowhile = false;
	public UtiWhile(BaseCode p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	public UtiBlock getBlock() {
		return block;
	}
	public void setBlock(UtiBlock code) {
		this.block = code;
	}
	public boolean isDowhile() {
		return dowhile;
	}
	public void setDowhile(boolean dowhile) {
		this.dowhile = dowhile;
	}
	public UtiAusdruck getExpression() {
		return expression;
	}
	public void setExpression(UtiAusdruck expression) {
		this.expression = expression;
	}
}
