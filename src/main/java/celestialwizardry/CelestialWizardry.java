package celestialwizardry;

import celestialwizardry.api.CWApi;
import celestialwizardry.client.model.OBJModels;
import celestialwizardry.config.Config;
import celestialwizardry.config.SettingHandler;
import celestialwizardry.config.spell.ConfigSpells;
import celestialwizardry.handler.CraftingHandler;
import celestialwizardry.handler.GlobalRuneConfigurationHandler;
import celestialwizardry.init.ModBlocks;
import celestialwizardry.init.ModItems;
import celestialwizardry.network.GuiHandler;
import celestialwizardry.network.PacketHandler;
import celestialwizardry.proxy.IProxy;
import celestialwizardry.reference.EventHandlers;
import celestialwizardry.reference.Reference;
import celestialwizardry.reference.Version;
import celestialwizardry.spellbook.SpellBook;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLFingerprintViolationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The main mod class of Minecraft mod Celestial Wizardry
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Version.VERSION,
     certificateFingerprint = Reference.FINGERPRINT, dependencies = Reference.DEPENDENCIES)
public class CelestialWizardry
{
    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.Instance(Reference.MOD_ID)
    public static CelestialWizardry instance;

    // Mod logger
    public static final Logger log = LogManager.getLogger(Reference.MOD_NAME.replace(" ", ""));

    // Mod configuration
    public static final Config config = new Config(Version.VERSION);

    // Spell configuration
    public static final ConfigSpells configSpells = new ConfigSpells(Version.VERSION);
    
    // Rune Configuration (per world)
    public static final GlobalRuneConfigurationHandler runeConfig = new GlobalRuneConfigurationHandler();

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

        // Initialize mod api logger
        CWApi.apiLog = LogManager.getLogger("CWApi");

        // Tell everyone that we are starting pre-initialization
        log.info("Starting pre-initialization");

        // Set configuration directory
        config.setConfigDir(event, Reference.MOD_ID.toLowerCase());

        // Initialize the configuration
        config.setConfiguration(Reference.MOD_NAME.replace(" ", ""));

        // Initialize the spell configuration
        configSpells.setConfiguration("Spells");

        // Initialize configuration settings
        SettingHandler.init();

        // Save configuration
        config.save();

        // Initialize mod items
        ModItems.init();

        // Initialize mod blocks
        ModBlocks.init();

        // Initialize mod entities
//        ModEntities.init(); DISABLED!!!

        // Initialize mod packet handler
        PacketHandler.init();

        // Register mod key bindings
        proxy.registerKeys();

        // Register entity event handler
        FMLCommonHandler.instance().bus().register(EventHandlers.ENTITY_EVENT_HANDLER);
        MinecraftForge.EVENT_BUS.register(EventHandlers.ENTITY_EVENT_HANDLER);

        // Register player event handler
        FMLCommonHandler.instance().bus().register(EventHandlers.PLAYER_EVENT_HANDLER);
        MinecraftForge.EVENT_BUS.register(EventHandlers.PLAYER_EVENT_HANDLER);
        
        //Register world event handler
        FMLCommonHandler.instance().bus().register(runeConfig);
        MinecraftForge.EVENT_BUS.register(runeConfig);
        
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

        // Initialize spell book data
        SpellBook.init();

        // Register render tick handlers
        proxy.registerRenderTickHandler();

        // Register mod renders
        proxy.registerRenderer();

        // Initialize models
        OBJModels.init();

        // Register spell casting event handler
        FMLCommonHandler.instance().bus().register(EventHandlers.SPELL_EVENT_HANDLER);
        MinecraftForge.EVENT_BUS.register(EventHandlers.SPELL_EVENT_HANDLER);

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

        // Initialize the spell configuration
        ConfigSpells.init();

        // Tell everyone that we have successfully post-initialized
        log.info("Finished post-initialization after " + (System.currentTimeMillis() - start) + " ms");
    }
}
