package uti.codeModel;

public class UtiZuweisung extends UtiCommand {
   UtiDesignator target;
   UtiAusdruck expression;
   public final static int BI_PLUS = 0;
	public final static int BI_MINUS = 1;
	public final static int BI_MULTIPLY = 2;
	public final static int BI_DIVIDE = 3;
	public final static int BI_NEG = 4;
	public final static int BI_OR = 4;
	public final static int BI_AND = 4;
	public final static int BI_NOT = 4;
	public final static int BI_XOR = 4;
	public final static int BI_LSHIFT = 4;
	public final static int BI_RSHIFT = 4;
	public final static int BI_SHIFTROUND = 4;
   int type=0;
   public UtiZuweisung(BaseCode p) {
	super(p);
	// TODO Auto-generated constructor stub
   }
public UtiAusdruck getExpression() {
	return expression;
}
public void setExpression(UtiAusdruck exp) {
	this.expression = exp;
}
public UtiDesignator getTarget() {
	return target;
}
public void setTarget(UtiDesignator t) {
	target = t;
}
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}
}
