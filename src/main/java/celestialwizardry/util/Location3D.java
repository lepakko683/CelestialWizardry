package celestialwizardry.util;

import net.minecraft.util.Vec3;

public class Location3D {
	
	private double x, y, z;
	
	public Location3D(double x, double y, double z) {
		this.setX(x);
		this.setY(y);
		this.setZ(z);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}
	
	public double getDistanceTo(Location3D b) {
		return Math.sqrt( Math.pow( this.x - b.getX(), 2 ) + Math.pow( this.y - b.getY(), 2 ) + Math.pow( this.z - b.getZ(), 2 ) );
	}
	
	public static double getDistance(double x, double y, double z, double x2, double y2, double z2) {
		return Math.sqrt( Math.pow( x - x2, 2 ) + Math.pow( y - y2, 2 ) + Math.pow( z - z2, 2) );
	}
	
	public static double getDistance(double x, double y, double z, Vec3 vec) {
		return Math.sqrt( Math.pow( x - vec.xCoord, 2 ) + Math.pow( y - vec.yCoord, 2 ) + Math.pow( z - vec.zCoord, 2) );
	}
}
