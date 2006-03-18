package uti.codeModel;

public class UtiIf extends UtiCommand {
   UtiAusdruck expression=new UtiAusdruck(this);
   UtiBlock then_block=new UtiBlock(this);
   UtiBlock else_block=new UtiBlock(this);
   public UtiIf(BaseCode p) {
	  super(p);
	  // TODO Auto-generated constructor stub
   }
public UtiBlock getElse_Block() {
	return else_block;
}
public void setElse_Block(UtiBlock else_block) {
	this.else_block = else_block;
}
public UtiAusdruck getExpression() {
	return expression;
}
public void setExpression(UtiAusdruck expression) {
	this.expression = expression;
}
public UtiBlock getThen_Block() {
	return then_block;
}
public void setThen_Block(UtiBlock then_block) {
	this.then_block = then_block;
}
}
