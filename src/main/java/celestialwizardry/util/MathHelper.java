package celestialwizardry.util;

/**
 * The reason to have our own math helper is that we don't need to change the method names after updates and our helper
 * has better documentation
 */
public class MathHelper
{
    /**
     * @param value = the number to clamp
     * @param max   = maximum possible value
     * @param min   = minimum possible value
     */
    public static double clampDouble(double value, double min, double max)
    {
        return (value < min) ? min : (value > max) ? max : value;
    }

    public static int clampZero_int(int value, int max)
    {
        return net.minecraft.util.MathHelper.clamp_int(value, 0, max);
    }

    public static float clampZero_float(float value, float max)
    {
        return net.minecraft.util.MathHelper.clamp_float(value, 0f, max);
    }

    public static double clampZero_double(double value, double max)
    {
        return net.minecraft.util.MathHelper.clamp_double(value, 0d, max);
    }
}
