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
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import celestialwizardry.CelestialWizardry;
import celestialwizardry.api.spellgrammar.Rune;
import celestialwizardry.config.RuneConfig;
import celestialwizardry.registry.RuneRegistry;

public class ServerRuneConfigurationHandler {
	public static final String CONFIG_FILE_NAME = "celestialWizardry_runeConfiguration.dat";
	public static final String CONFIG_FILE_BACKUP_NAME = "celestialWizardry_runeConfiguration.backup.dat";
	public static final String CONF_DIR_NAME = "cw_runeconfig";
	
	//TODO: USE LOGGER, HANDLE SWITHING WORLDS
	
	//register runes
	//load config
	//check registered runes and loaded config
	//assign correct rune ids for runes
	
	//Load after runes have been registered
	
	private static List<String> configNeedingPlayers = new ArrayList<String>();
	
	private static NBTTagCompound worldRuneConfig = null;
	private static boolean needToSave = false;
	private static RuneConfig configData = null;
	private static File WORLD_DIR = null;
	/**Used to avoid useless iterating through the config*/
	private static boolean configWasJustCreated = false;
//	private static final char separatorChr = File.separatorChar;
	private static boolean isClientServer = true;
	
	/**Called to setup configData*/
	private static void init(boolean created) {
		System.out.println("INIT!: " + created);
		configData = createConfigData();
		if(!created) {
			RuneRegistry.setupNumIds(configData);
			
			Iterator<String> names = configData.getRuneNames().iterator();
			String name = null;
			int wrcNid = 0; // Yes, I know WRC is a rally game :P
			int cdNid = 0;
//			System.out.println("SETUP: ");
			while(names.hasNext()) {
				name = names.next();
				if(name != null) {
					wrcNid = worldRuneConfig.getInteger(name);
					cdNid = configData.getNumId(name);
//					System.out.println(name + " wrcNid=" + wrcNid + " cdNid=" + cdNid);
					if(wrcNid == 0 && wrcNid != cdNid && cdNid != -1 && cdNid != 0) {
						if(cdNid != 0) {
							worldRuneConfig.setInteger(name, cdNid);
							if(!needToSave) {
								needToSave=true;
							}
						} else {
							System.err.println("o.O");
						}
					}
				}
			}
			
		}
	}
	
	@SuppressWarnings("rawtypes")
	private static RuneConfig createConfigData() {
		RuneConfig ret = new RuneConfig();
		
		Iterator keys = worldRuneConfig.func_150296_c().iterator();
		String key = null;
		while(keys.hasNext()) {
			key = (String)keys.next();
			if(key != null) {
				ret.addEntry(key, worldRuneConfig.getInteger(key)); // This is why runeIdsv index 0 is unused
			}
		}
		
		return ret;
	}
	
	private static void addToConfig(String runeStringID, int runeNumbericID) {
		worldRuneConfig.setInteger(runeStringID, runeNumbericID);
	}
	
	private static File getWorldRuneConfigFile() {
		return new File(getWorldRuneConfigDir(), CONFIG_FILE_NAME);
	}
	
	private static File getWorldRuneConfigBackupFile() {
		return new File(getWorldRuneConfigDir(), CONFIG_FILE_BACKUP_NAME);
	}
	
	private static File getWorldRuneConfigDir() {
		return new File(WORLD_DIR, CONF_DIR_NAME);
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
			
//			Iterator keys = configData.func_150296_c().iterator();
//			Object key = null;
//			System.out.println("LOADED: ");
//			while(keys.hasNext()) {
//				key = keys.next();
//				if(key != null) {
//					System.out.println((String) key);
//				}
//			}
			
			return configData;
		}
			
		return null;
	}
	
	private static boolean isConfigLoaded() {
		return worldRuneConfig != null && !worldRuneConfig.hasNoTags();
	}
	
	private static boolean saveWorldRuneConfig() {
		if(worldRuneConfig == null || worldRuneConfig.hasNoTags()) {
			return false;
		}
		try {
			if(!dirExists(WORLD_DIR)) {
				try {
					WORLD_DIR.mkdirs();
				} catch(Exception e) {
					CelestialWizardry.log.error("Failed to create config directory");
					e.printStackTrace();
				}
			}
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
				return false;
			}
			
			try {
				CompressedStreamTools.writeCompressed(worldRuneConfig, fos);
				fos.close();
			} catch(Exception e) {
				e.printStackTrace();
				CelestialWizardry.log.error("Something went wrong with writing into the world rune config file");
				return false;
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
				return false;
			}
			
			try {
				CompressedStreamTools.writeCompressed(worldRuneConfig, fos);
				fos.close();
			} catch(Exception e) {
				e.printStackTrace();
				CelestialWizardry.log.error("Something went wrong with writing into the world rune config backup file");
				return false;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			CelestialWizardry.log.error("Something went wrong with saving the world rune config!");
			return false;
		}
		return true;
	}
	
	private static boolean fileExists(File configFile) {
		return configFile != null && configFile.exists() && configFile.isFile();
	}
	
	private static boolean dirExists(File dir) {
		return dir != null && dir.exists() && dir.isDirectory();
	}
	
	/**Called at creation of a new world*/
	private static NBTTagCompound createConfigFromRegistry() {
		
		NBTTagCompound ret = new NBTTagCompound();
		RuneRegistry.onCreateConfig();
		
		String runeName = null;

		CelestialWizardry.log.info("Going to add " + RuneRegistry.getRuneCount() + " runes to the runeconfig.");
		for(int i=1;i<RuneRegistry.getRuneIdsvLength();i++) {
			runeName = RuneRegistry.getRuneNameForId(i);
			
			if(runeName != null) {
				ret.setInteger(runeName, i);
				CelestialWizardry.log.info("adding rune \"" + runeName + "\" to the config.");
			} else {
				CelestialWizardry.log.error("Rune \"" + runeName + "\" wasn't added to the config!!!");
			}
		}
		configWasJustCreated = true;
		return ret.hasNoTags() ? null : ret;
	}
	
	public static void saveConfigIfNeeded() {
		if(needToSave) {
			saveWorldRuneConfig();
		}
	}
	
	public static void onServerStarting(File worldDir) {
		isClientServer = !FMLCommonHandler.instance().getMinecraftServerInstance().isDedicatedServer();
		if(worldDir != null) {
			WORLD_DIR = worldDir;
		}
		File configFile = getWorldRuneConfigFile();
		File configFileBackup = getWorldRuneConfigBackupFile();
		
		if(fileExists(configFile)) {
			// The normal case when the world has already been created and is just being loaded.
			
			CelestialWizardry.log.info("Loading runeconfig for current world...");
			worldRuneConfig = loadWorldRuneConfig(configFile);
		}
		if(worldRuneConfig == null && fileExists(configFileBackup)) {
			// For some reason the main config file doesn't exist but the backup does, let's try to load that.
			
			CelestialWizardry.log.warn("Main config file wasn't found, attempting to load the backup...");
			worldRuneConfig = loadWorldRuneConfig(configFileBackup);
		}
		if(worldRuneConfig == null) {
			// Config file nor backup was loaded, we assume world is just being created.
			
			CelestialWizardry.log.warn("No config was found/loaded, creating a new one... (You don't need to mind this if world is being created.)");
			worldRuneConfig = createConfigFromRegistry();
			CelestialWizardry.log.info("Runeconfig save succeeded: " + saveWorldRuneConfig());
			if(worldRuneConfig != null) {
				init(true);
			}
		}
		if(!configWasJustCreated || worldRuneConfig == null) {
			if((worldRuneConfig != null && !worldRuneConfig.hasNoTags())) {
				init(false);
			} else {
				CelestialWizardry.log.error("Something went horribly wrong, ServerRuneConfigurationHandler was about to init, but world rune config still wasn't loaded!");
			}
		}
	}
	
	public static RuneConfig getConfigToSendToClients() {
		return configData;
	}
	
	public static void onServerStopping() {
		saveConfigIfNeeded();
		reset();
		RuneRegistry.reset(); // Handle elsewhere
	}
	
	private static void reset() {
		if(configNeedingPlayers != null) {
			configNeedingPlayers.clear();
		}
		worldRuneConfig = null;
		configWasJustCreated = false;
		configData = null;
	}
	
	/**Adds userName to the list of players' usernames that still need the runeconfig.*/
	public static void addConfigNeedingPlayer(String username) {
		if(configNeedingPlayers == null) {
			configNeedingPlayers = new ArrayList<String>();
		}
		configNeedingPlayers.add(username);
	}
}
