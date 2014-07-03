package celestialwizardry.util;

import net.minecraft.util.Vec3;

public class Loc3DF
{
    private float x, y, z;

    public Loc3DF(float x, float y, float z)
    {
        this.setX(x);
        this.setY(y);
        this.setZ(z);
    }

    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public float getZ()
    {
        return z;
    }

    public void setZ(float z)
    {
        this.z = z;
    }

    public float getDistanceTo(Location3D b)
    {
        return (float) Math
                .sqrt(Math.pow(this.x - b.getX(), 2) + Math.pow(this.y - b.getY(), 2) + Math.pow(this.z - b.getZ(), 2));
    }

    public static float getDistance(float x, float y, float z, float x2, float y2, float z2)
    {
        return (float) Math.sqrt(Math.pow(x - x2, 2) + Math.pow(y - y2, 2) + Math.pow(z - z2, 2));
    }

    public static float getDistance(float x, float y, float z, Vec3 vec)
    {
        return (float) Math
                .sqrt(Math.pow(x - vec.xCoord, 2) + Math.pow(y - vec.yCoord, 2) + Math.pow(z - vec.zCoord, 2));
    }
}
