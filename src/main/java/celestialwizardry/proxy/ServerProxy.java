package celestialwizardry.proxy;

import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.SERVER)
public class ServerProxy extends CommonProxy
{
    @Override
    public void registerKeyBindings()
    {
        // NO-OP
    }

    @Override
    public void registerRenderer()
    {
        // NO-OP
    }

    @Override
    public void sparkleFX(World world, double x, double y, double z, float r, float g, float b, float size, int m)
    {
        sparkleFX(world, x, y, z, r, g, b, size, m, false);
    }

    @Override
    public void sparkleFX(World world, double x, double y, double z, float r, float g, float b, float size, int m,
                          boolean fake)
    {
        // NO-OP
    }

    @Override
    public void setWispFXDistanceLimit(boolean limit)
    {
        // NO-OP
    }

    @Override
    public void wispFX(World world, double x, double y, double z, float r, float g, float b, float size)
    {
        wispFX(world, x, y, z, r, g, b, size, 0F);
    }

    @Override
    public void wispFX(World world, double x, double y, double z, float r, float g, float b, float size, float gravity)
    {
        wispFX(world, x, y, z, r, g, b, size, gravity, 1F);
    }

    @Override
    public void wispFX(World world, double x, double y, double z, float r, float g, float b, float size, float gravity,
                       float maxAgeMul)
    {
        wispFX(world, x, y, z, r, g, b, size, 0, -gravity, 0, maxAgeMul);
    }

    @Override
    public void wispFX(World world, double x, double y, double z, float r, float g, float b, float size, float motionx,
                       float motiony, float motionz)
    {
        wispFX(world, x, y, z, r, g, b, size, motionx, motiony, motionz, 1F);
    }

    @Override
    public void wispFX(World world, double x, double y, double z, float r, float g, float b, float size, float motionx,
                       float motiony, float motionz, float maxAgeMul)
    {
        // NO-OP
    }

}
