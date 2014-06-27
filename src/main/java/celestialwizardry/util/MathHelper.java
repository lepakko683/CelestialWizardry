package celestialwizardry.util;

/**The reason to have our own math helper is that we don't need to change the method names after updates and our helper has better documentation*/
public class MathHelper {
	
	/**
	 * @param value = the number to clamp
	 * @param max = maximum possible value
	 * @param min = minimum possible value
	 * */
	public static double clampDouble(double value, double min, double max) {
		return (value < min) ? min : (value > max) ? max : value;
	}
}
