package celestialwizardry.util;

public class Point {
	public float x, y;
	
	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float distanceTo(Point p) {
		return (float) Math.sqrt(p.x*p.x + p.y * p.y);
	}
	
}
