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

}
