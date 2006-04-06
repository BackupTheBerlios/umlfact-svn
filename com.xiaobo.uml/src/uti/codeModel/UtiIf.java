package uti.codeModel;

import org.w3c.dom.Element;

import uti.java.UtiOB;

public class UtiIf extends UtiCommand {
	UtiAusdruck expression = new UtiAusdruck(this);

	UtiBlock then_block = new UtiBlock(this);

	UtiBlock else_block = new UtiBlock(this);

	public UtiIf(BaseCode p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	public UtiBlock getElse_Block() {
		return else_block;
	}

	public void setElse_Block(UtiBlock else_block) {
	    else_block.setObjParent(this);
		this.else_block = else_block;
	}

	public UtiAusdruck getExpression() {
		return expression;
	}

	public void setExpression(UtiAusdruck expression) {
		expression.setObjParent(this);
		this.expression = expression;
	}

	public UtiBlock getThen_Block() {
		return then_block;
	}

	public void setThen_Block(UtiBlock then_block) {
		then_block.setObjParent(this);
		this.then_block = then_block;
	}

	public void read(Element xml, int version) {
		// TODO Auto-generated method stub
		super.read(xml, version);
		UtiOB.readObject(xml, "ausdruck", expression, version);
		UtiOB.readObject(xml, "then_block", then_block, version);
		UtiOB.readObject(xml, "else_block", else_block, version);
	}

	public void write(Element xml, int version) {
		// TODO Auto-generated method stub
		super.write(xml, version);
		UtiOB.writeObject(xml, "ausdruck", expression, version);
		UtiOB.writeObject(xml, "then_block", then_block, version);
		UtiOB.writeObject(xml, "else_block", else_block, version);
	}
	public void searchImports(ImportList list){
		expression.searchImports(list);
		if (then_block != null) {
		   then_block.searchImports(list);
		}
		if (else_block != null) {
		   else_block.searchImports(list);
		}
   }
	
}
