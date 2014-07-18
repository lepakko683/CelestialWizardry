package celestibytes.core.util;


public class Line {
	public Point a, b;
	
	public Line(Point a, Point b) {
		this.a = a;
		this.b = b;
	}
	
	public Line(float x1, float y1, float x2, float y2) {
		this.a = new Point(x1, y1);
		this.b = new Point(x2, y2);
	}
	
	public Line(Point a, float x, float y) {
		this.a = a;
		this.b = new Point(x, y);
	}
	
	/**@return null if there's no crossing point*/
	public Point getCrossPoint(Line l) {
		float i, j, k;
		return null;
	}
	
	public boolean isPointOnLine(Point p) {
		float rt1 = (this.b.y-this.a.y)/(this.b.x-this.a.x);
		float rt2 = (p.y-this.a.y)/(p.x-this.a.x);
		return Math.abs(rt2-rt1)<=0.000000000001f && ((p.distanceTo(this.a)+p.distanceTo(this.b))/2<=this.a.distanceTo(this.b));
	}
}
