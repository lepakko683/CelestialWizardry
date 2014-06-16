package celestialwizardry.proxy;

import net.minecraft.world.World;

public interface IProxy
{
    public void registerEventHandlers();

    public void registerTileEntities();

    public void registerKeyBindings();

    public void registerRenderer();

    public void sparkleFX(World world, double x, double y, double z, float r, float g, float b, float size, int m);

    public void sparkleFX(World world, double x, double y, double z, float r, float g, float b, float size, int m,
                          boolean fake);

    public void setWispFXDistanceLimit(boolean limit);

    public void wispFX(World world, double x, double y, double z, float r, float g, float b, float size);

    public void wispFX(World world, double x, double y, double z, float r, float g, float b, float size, float gravity);

    public void wispFX(World world, double x, double y, double z, float r, float g, float b, float size, float gravity,
                       float maxAgeMul);

    public void wispFX(World world, double x, double y, double z, float r, float g, float b, float size, float motionx,
                       float motiony, float motionz);

    public void wispFX(World world, double x, double y, double z, float r, float g, float b, float size, float motionx,
                       float motiony, float motionz, float maxAgeMul);
}
