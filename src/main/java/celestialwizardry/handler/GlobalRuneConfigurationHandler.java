package celestialwizardry.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import celestialwizardry.api.spellgrammar.Rune;
import celestialwizardry.registry.RuneRegistry;

public class GlobalRuneConfigurationHandler {
	public static final String CONFIG_FILE_NAME = "celestialWizardry_runeConfiguration.ini";
	public static final String CONFIG_FILE_BACKUP_NAME = "celestialWizardry_runeConfiguration.backup.ini"; // ".ini" - Why not? :)
	
	//TODO: USE LOGGER
	
	//register runes
	//load config
	//check registered runes and loaded config
	//assign correct rune ids for runes
	
	//Load after runes have been registered
	private static NBTTagCompound worldRuneConfig = null;
	private static boolean needToSave = false;
	private static Map<String, Integer> configData = null;
	private static int oldAvailableId = -1;
	private static String DIRPATH = null;
	
	public static void init() {
		Set<Entry<Integer, Rune>> runes = RuneRegistry.runeMap.entrySet();
		if(!runes.isEmpty()) {
			Iterator<Entry<Integer, Rune>> rite = runes.iterator();
			Entry<Integer, Rune> ent = null;
			while(rite.hasNext()) {
				ent = rite.next();
			}
		} else {
			System.err.println("Something went terribly wrong!!!"); 
		}
	}
	
	/**@return -1 if already correct*/
	private static int correctIfNeeded(int numbericID, Rune rune) {
		String runeId = Rune.getFullIdOf(rune);
		if(worldRuneConfig.hasKey(runeId)) {
			return worldRuneConfig.getInteger(runeId) == numbericID ? -1 : worldRuneConfig.getInteger(runeId);
		}
		
		//Key not found
		if(configData == null) {
			configData = new HashMap<String, Integer>();
			
			Iterator iter = worldRuneConfig.func_150296_c().iterator();
			Object key = null;
			while(iter.hasNext()) {
				key = iter.next();
				configData.put((String)key, worldRuneConfig.getInteger(runeId));
			}
		}
		
		int newId = getNextAvailableNumId();
		configData.put(runeId, newId);
		worldRuneConfig.setInteger(runeId, newId);
		needToSave = true;
		return newId == numbericID ? -1 : newId;
		
	}
	
	private static int getNextAvailableNumId() {
		int id = oldAvailableId++;
		while(configData.containsValue(id)) {
			id++;
		}
		oldAvailableId = id;
		return id;
	}
	
	private static void addToConfig(String runeStringID, int runeNumbericID) {
		worldRuneConfig.setInteger(runeStringID, runeNumbericID);
	}
	
	private static File getWorldRuneConfigFile(String worldDir) {
		try{
			File confFile = new File(worldDir + CONFIG_FILE_NAME);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static File getWorldRuneConfigBackupFile(String worldDir) {
		try{
			File confFile = new File(worldDir + CONFIG_FILE_BACKUP_NAME);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
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
	//TODO: finish!!!
	private static boolean saveWorldRuneConfig(File configFile) {
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream(configFile);
		} catch(Exception e) {
			e.printStackTrace();
			System.err.println("Couldn't open ");
		}
		return false;
	}
	
	private static boolean fileExists(File configFile) {
		return configFile != null && configFile.exists() && configFile.isFile();
	}
	
	private static NBTTagCompound createConfigFromRegistry() {
		return null;
	}
	
	public static void saveConfigIfNeeded() {
		if(needToSave) {
			
		}
	}
	
	public static void onServerStarting(String worldDir) {
		File configFile = getWorldRuneConfigFile(worldDir);
		File configFileBackup = getWorldRuneConfigBackupFile(worldDir);
		
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
			
			
		}
		if(worldRuneConfig != null && !worldRuneConfig.hasNoTags()) {
			init();
		} else {
			System.err.println("Something went horribly wrong, GlobalRuneConfigurationHandler was about to init, but world rune config still wasn't loaded!");
		}
		
		
	}
	
	public static void onServerStopping(String worldDir) {
		
	}
	
	
}
