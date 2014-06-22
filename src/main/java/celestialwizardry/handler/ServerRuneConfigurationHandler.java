package celestialwizardry.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import celestialwizardry.CelestialWizardry;
import celestialwizardry.api.spellgrammar.Rune;
import celestialwizardry.config.RuneConfig;
import celestialwizardry.registry.RuneRegistry;

public class ServerRuneConfigurationHandler {
	public static final String CONFIG_FILE_NAME = "celestialWizardry_runeConfiguration.dat";
	public static final String CONFIG_OLD_FILE_NAME = "celestialWizardry_runeConfiguration.old.dat";
	public static final String CONFIG_FILE_BACKUP_NAME = "celestialWizardry_runeConfiguration.backup.dat";
	public static final String CONFIG_OLD_FILE_BACKUP_NAME = "celestialWizardry_runeConfiguration.old.backup.dat";
	public static final String CONF_DIR_NAME = "cw_runeconfig";
	
	//TODO: USE LOGGER, HANDLE SWITHING WORLDS
	
	//register runes
	//load config
	//check registered runes and loaded config
	//assign correct rune ids for runes
	
	//Load after runes have been registered
	
	private static List<String> configNeedingPlayers = new ArrayList<String>();
	
	private static boolean isConfigSetup = false;
	private static NBTTagCompound worldRuneConfig = null;
	private static boolean needToSave = false;
	private static RuneConfig configData = null;
	private static String WORLD_DIR = null;
	/**Used to prevent useless iterating through the config*/
	private static boolean configWasJustCreated = false;
	private static final char separatorChr = File.separatorChar;
	private static boolean isClientServer = true; // TODO: figure out if running on client server (do we add "saves/" to the world path)
	
	public static void init() {
		RuneRegistry.setupNumIds(configData);
	}
	
	/**@return -1 if already correct*/
	
	private static void addToConfig(String runeStringID, int runeNumbericID) {
		worldRuneConfig.setInteger(runeStringID, runeNumbericID);
	}
	
	private static File getWorldRuneConfigFile() {
		return new File((isClientServer ? "saves" + separatorChr : "") + WORLD_DIR + separatorChr + CONF_DIR_NAME + separatorChr + CONFIG_FILE_NAME);
	}
	
	private static File getWorldRuneConfigBackupFile() {
		return new File((isClientServer ? "saves" + separatorChr : "") + WORLD_DIR + separatorChr + CONF_DIR_NAME + separatorChr + CONFIG_FILE_BACKUP_NAME);
	}
	
	private static File getWorldRuneConfigDir() {
		return new File((isClientServer ? "saves" + separatorChr : "") + WORLD_DIR + separatorChr + CONF_DIR_NAME);
	}
	
	/**@param configFile - The file containing the config data */
	private static NBTTagCompound loadWorldRuneConfig(File configFile) {
		FileInputStream fis = null;
		NBTTagCompound configData = null;
		
		try {
			fis = new FileInputStream(configFile);
		} catch(Exception e) {
			e.printStackTrace();
			System.err.println("Couldn't open world rune config file");
			return null;
		}
		
		try {
			configData = CompressedStreamTools.readCompressed(fis);
			fis.close(); //Never forget to close your streams ;)
		} catch(Exception e) {
			e.printStackTrace();
			System.err.println("Couldn't read world rune config");
			return null;
		}
		
		if(configData != null && !configData.hasNoTags()) {
			// Config was loaded successfully
			return configData;
		}
			
		return null;
	}
	
	private static boolean saveWorldRuneConfig() {
		try {
			
			File mainConfigFile = getWorldRuneConfigFile();
			File backupConfigFile = getWorldRuneConfigBackupFile();
			
			boolean deleteSucceeded = false;
			if(fileExists(mainConfigFile)) {
				deleteSucceeded = mainConfigFile.delete();
			}
			
			if(!deleteSucceeded) {
				CelestialWizardry.log.warn("The deletion of the old rune configuration file failed. This might cause problems!");
			}
			
			mainConfigFile = getWorldRuneConfigFile();
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(mainConfigFile);
			} catch(Exception e) {
				e.printStackTrace();
				CelestialWizardry.log.error("Couldn't open FileOutputStream for mainConfigFile");
			}
			
			try {
				CompressedStreamTools.writeCompressed(worldRuneConfig, fos);
				fos.close();
			} catch(Exception e) {
				e.printStackTrace();
				CelestialWizardry.log.error("Something went wrong with writing into the world rune config file");
			}
			
			// SAVE THE BACKUP
			deleteSucceeded = false;
			if(fileExists(backupConfigFile)) {
				deleteSucceeded = backupConfigFile.delete();
			}
			
			if(!deleteSucceeded) {
				CelestialWizardry.log.warn("The deletion of the old rune configuration backup file failed. This might cause problems!");
			}
			
			try {
				fos = new FileOutputStream(backupConfigFile);
			} catch(Exception e) {
				e.printStackTrace();
				CelestialWizardry.log.error("Couldn't open FileOutputStream for backupConfigFile");
			}
			
			try {
				CompressedStreamTools.writeCompressed(worldRuneConfig, fos);
				fos.close();
			} catch(Exception e) {
				e.printStackTrace();
				CelestialWizardry.log.error("Something went wrong with writing into the world rune config backup file");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			CelestialWizardry.log.error("Something went wrong with saving the world rune config!");
		}
		return false;
	}
	
	private static boolean fileExists(File configFile) {
		return configFile != null && configFile.exists() && configFile.isFile();
	}
	
	/**Called at creation of a new world*/
	private static NBTTagCompound createConfigFromRegistry() {
		
		NBTTagCompound ret = new NBTTagCompound();
		RuneRegistry.onCreateConfig();
		
		String runeName = null;
		
		for(int i=0;i<RuneRegistry.runeIds.size();i++) {
			runeName = RuneRegistry.runeIds.get(i);
			
			if(runeName != null) {
				ret.setInteger(runeName, i);
			} else {
				CelestialWizardry.log.error("Rune \"" + runeName + "\" wasn't added to the config!!!");
			}
		}
		return ret.hasNoTags() ? null : ret;
	}
	
	public static void saveConfigIfNeeded() {
		if(needToSave) {
			saveWorldRuneConfig();
		}
	}
	
	public static void onServerStarting(String worldDir) {
		WORLD_DIR = worldDir;
		File configFile = getWorldRuneConfigFile();
		File configFileBackup = getWorldRuneConfigBackupFile();
		
		if(fileExists(configFile)) {
			// The normal case when the world has already been created and is just being loaded.
			
			worldRuneConfig = loadWorldRuneConfig(configFile);
			
		}
		if(worldRuneConfig == null && fileExists(configFileBackup)) {
			// For some reason the main config file doesn't exist but the backup does, let's try to load that.
			
			System.out.println("Main config file wasn't found, attempting to load the backup");
			worldRuneConfig = loadWorldRuneConfig(configFileBackup);
			
		}
		if(worldRuneConfig == null) {
			// Config file nor backup was loaded, we assume world is just being created.
			
			System.out.println("No config was found, creating a new one...");
			worldRuneConfig = createConfigFromRegistry();
			
		}
		if((worldRuneConfig != null && !worldRuneConfig.hasNoTags())) {
			init();
		} else {
			System.err.println("Something went horribly wrong, GlobalRuneConfigurationHandler was about to init, but world rune config still wasn't loaded!");
		}
		
		
	}
	
	// TODO!
	/**Missing method body!*/
	public static RuneConfig getConfigToSendToClients() {
		if(isConfigSetup) {
			RuneConfig ret = new RuneConfig();
			
			ret.addEntry("", -1);
		}
		return null;
	}
	
	public static void onServerStopping() {
		saveConfigIfNeeded();
		reset();
		RuneRegistry.reset();
	}
	
	private static void reset() {
		configNeedingPlayers.clear();
	}
	
	/**Adds userName to the list of players' usernames that still need the runeconfig.*/
	public static void addConfigNeedingPlayer(String username) {
		if(configNeedingPlayers == null) {
			configNeedingPlayers = new ArrayList<String>();
		}
		configNeedingPlayers.add(username);
	}
	
	public static void test(String worldDirName) {
		isClientServer = !FMLCommonHandler.instance().getMinecraftServerInstance().isDedicatedServer();
		System.out.println("Is singleplayer: " + isClientServer);
		WORLD_DIR = worldDirName;
		File runeConfMain = getWorldRuneConfigFile();
		
		if(!fileExists(runeConfMain)) {
			try {
			getWorldRuneConfigDir().mkdirs();
			runeConfMain.createNewFile();
			} catch(Exception e) {
				System.err.println("Failed to create file!");
				e.printStackTrace();
			}
		}
	}
	
	
}
