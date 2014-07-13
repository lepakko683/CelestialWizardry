package celestibytes.celestialwizardry.crystal;

import celestibytes.celestialwizardry.crystal.init.CrystalBlocks;
import celestibytes.celestialwizardry.crystal.init.CrystalItems;
import celestibytes.celestialwizardry.crystal.proxy.ICrystalProxy;
import celestibytes.celestialwizardry.crystal.reference.CrystalReference;

import cpw.mods.fml.common.SidedProxy;

public class Crystals
{
    @SidedProxy(clientSide = CrystalReference.CLIENT_PROXY_CLASS, serverSide = CrystalReference.SERVER_PROXY_CLASS)
    public static ICrystalProxy proxy;

    public static void preInit()
    {
        // Initialize crystal items
        CrystalItems.init();

        // Initialize crystal blocks
        CrystalBlocks.init();
    }

    public static void init()
    {
        // Register crystal renders
        proxy.registerRenderer();
    }

    public static void postInit()
    {

    }
}
