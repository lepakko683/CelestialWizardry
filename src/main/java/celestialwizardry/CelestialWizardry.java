package celestialwizardry;

import celestialwizardry.client.render.Renderables;
import celestialwizardry.config.Config;
import celestialwizardry.config.SettingHandler;
import celestialwizardry.config.spell.ConfigSpells;
import celestialwizardry.crystal.Crystals;
import celestialwizardry.entity.EntityBell;
import celestialwizardry.handler.CraftingHandler;
import celestialwizardry.handler.ServerRuneConfigurationHandler;
import celestialwizardry.init.InitRunes;
import celestialwizardry.init.ModBlocks;
import celestialwizardry.init.ModItems;
import celestialwizardry.network.GuiHandler;
import celestialwizardry.network.PacketHandler;
import celestialwizardry.proxy.IProxy;
import celestialwizardry.reference.Reference;
import celestialwizardry.reference.Version;
import celestialwizardry.spellbook.SpellBook;
import celestialwizardry.util.LogHelper;
import celestialwizardry.world.WorldGenerator;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLFingerprintViolationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

import celestibytes.core.mod.CelestiMod;

import java.io.File;

/**
 * The main mod class of Minecraft mod Celestial Wizardry
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Version.VERSION,
     certificateFingerprint = Reference.FINGERPRINT, dependencies = Reference.DEPENDENCIES,
     guiFactory = Reference.GUI_FACTORY_CLASS)
public class CelestialWizardry extends CelestiMod
{
    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.Instance(Reference.MOD_ID)
    public static CelestialWizardry instance;

    // Mod configuration
    public static final Config config = new Config(Version.VERSION);

    // Spell configuration
    public static final ConfigSpells configSpells = new ConfigSpells(Version.VERSION);

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
    public void onServerStart(FMLServerStartingEvent event)
    {
        LogHelper.info("SERVER STARTING EVENT!!!!!");
        System.out.println("Server folder name: " + event.getServer().getFolderName() + " @ CW.class");
        ServerRuneConfigurationHandler.onServerStarting(
                new File(FMLCommonHandler.instance().getSavesDirectory(), event.getServer().getFolderName()));
    }

    @Mod.EventHandler
    public void onServerStop(FMLServerStoppingEvent event)
    {
        LogHelper.info("SERVER STOPPING EVENT!!!!!");
        ServerRuneConfigurationHandler.onServerStopping();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        // Set the start time for tracking
        long start = System.currentTimeMillis();

        // Tell everyone that we are starting pre-initialization
        LogHelper.info("Starting pre-initialization");

        // Set configuration directory
        config.setConfigDir(event, Reference.MOD_ID.toLowerCase());

        // Initialize the configuration
        config.setConfiguration(Reference.MOD_NAME.replace(" ", ""));

        // Initialize the spell configuration
        configSpells.setConfiguration("Spells");

        // Initialize configuration settings
        SettingHandler.sync();

        // Save configuration
        config.save();

        // Initialize mod items
        ModItems.init();

        // Initialize mod blocks
        ModBlocks.init();

        // Initialize mod entities
//        ModEntities.init(); DISABLED!!!
        EntityRegistry.registerModEntity(EntityBell.class, "Bell", 1, CelestialWizardry.instance, 64, 1, true);
        
        // Initialize runeRegistries
        proxy.initializeRuneRegistries();

        // Initialize mod packet handler
        PacketHandler.init();

        // Register mod key bindings
//        proxy.registerKeyBindings(); >:)

        // Pre-initialize the crystals
        Crystals.preInit();

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

        // Register gui handler
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());

        // Register mod tile entities
        proxy.registerTileEntities();

        // Register Event Handlers
        proxy.registerEventHandlers();

        // Initialize crafting handler
        CraftingHandler.init(); // TODO Add the class to FML event subscription

        // Initialize spell book data
        SpellBook.init();

        // Register mod renders
        proxy.registerRenderer();

        // Initialize renderables
        Renderables.init();

        // Register world generator
        GameRegistry.registerWorldGenerator(new WorldGenerator(), 0);

        // Initialize the crystals
        Crystals.init();

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

        // Initialize runes
        InitRunes.init();

        // Initialize the spell configuration
        ConfigSpells.init();

        // Post-initialize the crystals
        Crystals.postInit();

        // Tell everyone that we have successfully post-initialized
        LogHelper.info("Finished post-initialization after " + (System.currentTimeMillis() - start) + " ms");
    }
}
