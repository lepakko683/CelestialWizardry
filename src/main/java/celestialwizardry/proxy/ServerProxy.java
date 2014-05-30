package celestialwizardry.proxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.SERVER)
public class ServerProxy extends CommonProxy
{
    @Override
    public void registerKeys()
    {
        // NOOP
    }

    @Override
    public void registerRenderTickHandler()
    {
        // NOOP
    }

    @Override
    public void registerRenderer()
    {
        // NOOP
    }
}
