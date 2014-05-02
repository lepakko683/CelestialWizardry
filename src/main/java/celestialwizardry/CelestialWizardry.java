package celestialwizardry;

import celestialwizardry.config.ConfigHandler;
import celestialwizardry.handler.CraftingHandler;
import celestialwizardry.handler.GuiHandler;
import celestialwizardry.init.ModBlocks;
import celestialwizardry.init.ModItems;
import celestialwizardry.network.PacketHandler;
import celestialwizardry.proxy.IProxy;
import celestialwizardry.reference.Reference;
import celestialwizardry.reference.Version;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * The main mod class of Minecraft mod Celestial Wizardry
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Version.VERSION)
public class CelestialWizardry
{
    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.Instance
    public static CelestialWizardry instance;

    public static Logger log;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        // Set the start time for tracking
        long start = System.currentTimeMillis();

        // Initialize mod logger
        log = event.getModLog();

        // Tell everyone that we are starting pre-initialization
        log.info("Starting pre-initialization");

        // Initialize the configuration
        ConfigHandler.init(
                event.getModConfigurationDirectory().getAbsolutePath() + File.separator + Reference.MOD_ID.toLowerCase()
                        + File.separator
                          );

        // Initialize mod items
        ModItems.init();

        // Initialize mod blocks
        ModBlocks.init();

        // Initialize mod packet handler
        PacketHandler.init();

        // Tell everyone that we have successfully pre-initialized
        log.info("Finished pre-initialization after " + (System.currentTimeMillis() - start) + " ms");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        // Set the start time for tracking
        long start = System.currentTimeMillis();

        // Tell everyone that we are starting initialization
        log.info("Starting initialization");

        // Register gui handler
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());

        // Register mod tile entities
        proxy.registerTileEntities();

        // Initialize crafting handler
        CraftingHandler.init(); // TODO Add the class to FML event subscription

        // Tell everyone that we have successfully initialized
        log.info("Finished initialization after " + (System.currentTimeMillis() - start) + " ms");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        // Set the start time for tracking
        long start = System.currentTimeMillis();

        // Tell everyone that we are starting post-initialization
        log.info("Starting post-initialization");

        // Tell everyone that we have successfully post-initialized
        log.info("Finished post-initialization after " + (System.currentTimeMillis() - start) + " ms");
    }
}
