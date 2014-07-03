package celestialwizardry.crystal.reference;

import celestialwizardry.crystal.handler.CrystalWorldEventHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CrystalEventHandlers
{
    @SideOnly(Side.CLIENT)
    public static class Client
    {

    }

    public static class Common
    {
        public static final CrystalWorldEventHandler CRYSTAL_WORLD_EVENT_HANDLER = new CrystalWorldEventHandler();
    }
}
