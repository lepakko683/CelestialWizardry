package celestibytes.core;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import celestibytes.core.config.Config;
import celestibytes.core.config.SettingHandler;
import celestibytes.core.mod.CelestiMod;
import celestibytes.core.proxy.IProxy;
import celestibytes.core.reference.Reference;
import celestibytes.core.reference.Version;
import celestibytes.core.util.LogHelper;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION,
     certificateFingerprint = Reference.FINGERPRINT, dependencies = Reference.DEPENDENCIES,
     guiFactory = Reference.GUI_FACTORY_CLASS)
public class CelestiCore extends CelestiMod
{
    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.Instance(Reference.MOD_ID)
    public static CelestiCore instance;

    // Mod configuration
    public static final Config config = new Config(Version.VERSION);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        // Set the start time for tracking
        long start = System.currentTimeMillis();

        // Tell everyone that we are starting pre-initialization
        LogHelper.info("Starting pre-initialization");

        // Set configuration directory
        config.setConfigDir(event, celestialwizardry.reference.Reference.MOD_ID.toLowerCase());

        // Initialize the configuration
        config.setConfiguration(celestialwizardry.reference.Reference.MOD_NAME.replace(" ", ""));

        // Initialize configuration settings
        SettingHandler.sync();

        // Save configuration
        config.save();

        // Tell everyone that we have successfully pre-initialized
        LogHelper.info("Finished pre-initialization after " + (System.currentTimeMillis() - start) + " ms");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        // Set the start time for tracking
        long start = System.currentTimeMillis();

        // Tell everyone that we are starting initialization
        LogHelper.info("Starting initialization");

        // Register Event Handlers
        proxy.registerEventHandlers();

        // Tell everyone that we have successfully initialized
        LogHelper.info("Finished initialization after " + (System.currentTimeMillis() - start) + " ms");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        // Set the start time for tracking
        long start = System.currentTimeMillis();

        // Tell everyone that we are starting post-initialization
        LogHelper.info("Starting post-initialization");

        // Tell everyone that we have successfully post-initialized
        LogHelper.info("Finished post-initialization after " + (System.currentTimeMillis() - start) + " ms");
    }

    @Override
    public String getId()
    {
        return CelestiCore.class.getAnnotation(Mod.class).modid();
    }

    @Override
    public String getName()
    {
        return CelestiCore.class.getAnnotation(Mod.class).name();
    }

    @Override
    public String getVersion()
    {
        return CelestiCore.class.getAnnotation(Mod.class).version();
    }
}
