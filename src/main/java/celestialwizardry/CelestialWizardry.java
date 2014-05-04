package celestialwizardry;

import celestialwizardry.api.CWApi;
import celestialwizardry.config.ConfigHandler;
import celestialwizardry.config.SettingHandler;
import celestialwizardry.handler.CraftingHandler;
import celestialwizardry.handler.GuiHandler;
import celestialwizardry.init.ModBlocks;
import celestialwizardry.init.ModItems;
import celestialwizardry.network.PacketHandler;
import celestialwizardry.proxy.IProxy;
import celestialwizardry.reference.Reference;
import celestialwizardry.reference.Settings;
import celestialwizardry.reference.Version;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLFingerprintViolationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * The main mod class of Minecraft mod Celestial Wizardry
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Version.VERSION,
     certificateFingerprint = Reference.FINGERPRINT, dependencies = Reference.DEPENDENCIES)
public class CelestialWizardry
{
    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.Instance
    public static CelestialWizardry instance;

    // Mod logger
    public static Logger log;

    // Mod configuration
    public static final ConfigHandler config = new ConfigHandler(Version.VERSION);

    @Mod.EventHandler
    public void invalidFingerprint(FMLFingerprintViolationEvent event)
    {
        if (Reference.FINGERPRINT.equals("@FINGERPRINT@"))
        {
            FMLLog.severe("The copy of " + Reference.MOD_NAME
                                  + " that you are running is a development version of the mod, " +
                                  "and as such may be unstable and/or incomplete.");
        }
        else
        {
            FMLLog.severe("The copy of " + Reference.MOD_NAME
                                  + " that you are running has been modified from the original, " +
                                  "and unpredictable things may happen. Please consider re-downloading the original " +
                                  "version of the mod.");
        }
    }

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
        config.setConfiguration(new Configuration(
                new File(event.getModConfigurationDirectory(), "/celestialwizardry/celestialwizardry.cfg")));

        // Initialize configuration settings
        SettingHandler.init();

        // Save configuration
        config.save();

        // Initialize mod items
        ModItems.init();

        // Initialize mod blocks
        ModBlocks.init();

        // Initialize mod packet handler
        PacketHandler.init();

        // Register mod key bindings
        proxy.registerKeys();

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
