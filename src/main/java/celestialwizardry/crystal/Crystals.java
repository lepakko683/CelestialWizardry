package celestialwizardry.crystal;

import celestialwizardry.crystal.init.CrystalBlocks;
import celestialwizardry.crystal.init.CrystalItems;
import celestialwizardry.crystal.proxy.ICrystalProxy;
import celestialwizardry.crystal.reference.CrystalReference;

import cpw.mods.fml.common.SidedProxy;

public class Crystals
{
    @SidedProxy(clientSide = CrystalReference.CLIENT_PROXY_CLASS, serverSide = CrystalReference.SERVER_PROXY_CLASS)
    public static ICrystalProxy proxy;

    public static void preInit()
    {
        CrystalItems.init();

        CrystalBlocks.init();
    }

    public static void init()
    {
        // Register crystal tile entities
        proxy.registerTileEntities();

        // Register Crystal Event Handlers
        proxy.registerEventHandlers();

        // Register crystal renders
        proxy.registerRenderer();
    }

    public static void postInit()
    {

    }
}
