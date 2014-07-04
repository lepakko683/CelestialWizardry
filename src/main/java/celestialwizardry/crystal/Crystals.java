package celestialwizardry.crystal;

import celestialwizardry.crystal.init.CrystalBlocks;
import celestialwizardry.crystal.init.CrystalItems;
import celestialwizardry.crystal.proxy.ICrystalProxy;
import celestialwizardry.crystal.reference.CrystalReference;
import celestialwizardry.handler.ServerRuneConfigurationHandler;
import celestialwizardry.util.LogHelper;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;

import java.io.File;

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
        // Register Crystal Event Handlers
        proxy.registerEventHandlers();

        // Register crystal renders
        proxy.registerRenderer();
    }

    public static void postInit()
    {

    }
}
