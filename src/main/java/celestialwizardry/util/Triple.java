package celestialwizardry.util;

public class Triple {
	
	private Object a,b,c;
	
	public Triple(Object a, Object b, Object c) {
		this.a=a;
		this.b=b;
		this.c=c;
	}
	
	public Object getA() {
		return this.a;
	}
	
	public Object getB() {
		return this.b;
	}
	
	public Object getC() {
		return this.c;
	}
	
	public void setA(Object o) {
		this.a=o;
	}
	
	public void setB(Object o) {
		this.b=o;
	}
	
	public void setC(Object o) {
		this.c=o;
	}
	
}
