package uti.codeModel;

import java.util.*;

public class UtiAusdruck extends BaseCode {
	public final static int T_CALL = 0;
	public final static int T_AUSDRUCK = 1;
	public final static int T_INT_CONST = 2;
	public final static int T_FLOAT_CONST = 3;
	public final static int T_BUILD_IN = 4;
	
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
    UtiCall call=null;
    UtiAusdruck ausdruck=null;
    UtiAusdruck ausdruck2=null;
    long number=0;
    double real=0;
    int build_in=0;
    int type=0;
	public UtiAusdruck(BaseCode p) {
		super(p);
		
	}
    void setCall(UtiCall call) {
    	
    }
	public UtiAusdruck getAusdruck() {
		return ausdruck;
	}
	public void setAusdruck(UtiAusdruck ausdruck) {
		this.ausdruck = ausdruck;
	}
	public UtiAusdruck getSecoundAusdruck() {
		return ausdruck2;
	}
	public void setSecoundAusdruck(UtiAusdruck ausdruck2) {
		this.ausdruck2 = ausdruck2;
	}
	public int getBuild_in() {
		return build_in;
	}
	public void setBuild_in(int build_in) {
		this.build_in = build_in;
	}
	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
	}
	public double getReal() {
		return real;
	}
	public void setReal(double real) {
		this.real = real;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public UtiCall getCall() {
		return call;
	}
	
}
