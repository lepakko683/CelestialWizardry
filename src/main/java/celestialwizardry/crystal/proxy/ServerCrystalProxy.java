package celestialwizardry.crystal.proxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.SERVER)
public class ServerCrystalProxy extends CommonCrystalProxy
{
    @Override
    public void registerRenderer()
    {
        // NO-OP
    }
}
