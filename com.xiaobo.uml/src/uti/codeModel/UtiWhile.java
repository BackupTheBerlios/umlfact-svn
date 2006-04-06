package uti.codeModel;

import org.w3c.dom.Element;

import uti.java.UtiOB;

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
		code.setObjParent(this);
		this.block = code;
	}
	public boolean isDoWhile() {
		return dowhile;
	}
	public void setDoWhile(boolean dowhile) {
		this.dowhile = dowhile;
	}
	public UtiAusdruck getExpression() {
		return expression;
	}
	public void setExpression(UtiAusdruck expression) {
		expression.setObjParent(this);
		this.expression = expression;
	}
	public void read(Element xml, int version) {
		// TODO Auto-generated method stub
		super.read(xml, version);
		UtiOB.readObject(xml, "block", block, version);
		UtiOB.readObject(xml, "expression", expression, version);
		setDoWhile(UtiOB.readBoolean(xml, "dowhile", false));
		
	}

	public void write(Element xml, int version) {
		// TODO Auto-generated method stub
		super.write(xml, version);
		UtiOB.writeObject(xml, "block", block, version);
		UtiOB.writeObject(xml, "expression", expression, version);
		UtiOB.writeBoolean(xml, "dowhile", isDoWhile());
	}
	public void searchImports(ImportList list){		
		expression.searchImports(list);
		block.searchImports(list);
    }
}
