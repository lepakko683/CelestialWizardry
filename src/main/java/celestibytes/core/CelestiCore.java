package celestibytes.core;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import celestibytes.core.config.Config;
import celestibytes.core.config.ConfigBase;
import celestibytes.core.config.SettingHandler;
import celestibytes.core.mod.CelestiMod;
import celestibytes.core.mod.version.ModVersion;
import celestibytes.core.proxy.IProxy;
import celestibytes.core.reference.Reference;
import celestibytes.core.reference.Settings;
import celestibytes.core.reference.Version;
import celestibytes.core.util.log.CoreLogHelper;

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
        CoreLogHelper.info("Starting pre-initialization");

        // Set configuration directory
        config.setConfigDir(event, ConfigBase.DOMAIN);

        // Initialize the configuration
        config.setConfiguration(Reference.MOD_NAME.replace(" ", ""));

        // Initialize configuration settings
        SettingHandler.sync();

        // Save configuration
        config.save();

        // Tell everyone that we have successfully pre-initialized
        CoreLogHelper.info("Finished pre-initialization after " + (System.currentTimeMillis() - start) + " ms");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        // Set the start time for tracking
        long start = System.currentTimeMillis();

        // Tell everyone that we are starting initialization
        CoreLogHelper.info("Starting initialization");

        // Register Event Handlers
        proxy.registerEventHandlers();

        // Tell everyone that we have successfully initialized
        CoreLogHelper.info("Finished initialization after " + (System.currentTimeMillis() - start) + " ms");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        // Set the start time for tracking
        long start = System.currentTimeMillis();

        // Tell everyone that we are starting post-initialization
        CoreLogHelper.info("Starting post-initialization");

        // Tell everyone that we have successfully post-initialized
        CoreLogHelper.info("Finished post-initialization after " + (System.currentTimeMillis() - start) + " ms");
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
    public String getTargetLog()
    {
        return Reference.MOD_NAME;
    }

    @Override
    public String getVersion()
    {
        return CelestiCore.class.getAnnotation(Mod.class).version();
    }

    @Override
    public ModVersion getModVersion()
    {
        return Version.MOD_VERSION;
    }

    @Override
    public boolean allowVersionCheck()
    {
        return Settings.enableVersionCheck;
    }

    @Override
    public boolean allowVersionNote()
    {
        return Settings.enableVersionNotification;
    }

    @Override
    protected String channel()
    {
        return getModVersion().getChannel().getKey();
    }

    @Override
    protected String updateChannel()
    {
        return Settings.channel;
    }
}
