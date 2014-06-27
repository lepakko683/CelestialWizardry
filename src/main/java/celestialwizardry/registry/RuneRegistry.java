package celestialwizardry.registry;

import celestialwizardry.CelestialWizardry;
import celestialwizardry.api.spellgrammar.Rune;
import celestialwizardry.config.RuneConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.minecraft.util.ResourceLocation;

public abstract class RuneRegistry
{
	// Rune id 0 is intentionally unused!!!
	public static Map<String,ResourceLocation[]> runeTexLocs = new HashMap<String, ResourceLocation[]>();
    public static Map<String, Rune> runeMap = new HashMap<String, Rune>();
    private static String[] runeIdsv = null;
    
    /**Are the numIds set up*/
    private static boolean configLoaded = false;
    
    public static void registerRune(Rune rune)
    {
        String name = Rune.getFullIdOf(rune);
        
        if(!runeMap.containsKey(name)) {
        	CelestialWizardry.log.info("Registering rune " + name);
        	runeMap.put(name, rune);
        	return;
        }
        
        CelestialWizardry.log.warn("Trying to register duplicate rune, skipping!");
    }
    
    /**Called from *RuneConfigurationHandler. Sets numeric ids for runes from config*/
    public static void setupNumIds(RuneConfig config) {
    	if(config == null) {
    		return;
    	}
    	int runeCount = config.getRuneCount();
    	if(runeCount == 0) {
    		CelestialWizardry.log.error("Attempted to setup runeconfig but config's runecount is zero.");
    		return; // TODO throw exception
    	}
    	if(runeIdsv != null) {
    		CelestialWizardry.log.error("Attempted to setup runeconfig but runeIdsv array wasn't null (runeconfig might have already been set up. If not you will most likely crash.).");
    		return; // TODO throw exception
    	}
    	runeIdsv = new String[runeMap.size()];
    	if(!configLoaded) {
    		Iterator<String> rNames = runeMap.keySet().iterator();
    		runeIdsv = new String[runeMap.size()+1];
    		String cName = null;
    		int nid = -1;
    		while (rNames.hasNext()) {
    			cName = rNames.next();
    			nid = config.getNumId(cName);
    			
    			if(nid == 0) {
    				nid = config.setNumIdAutoFor(cName);
    				System.out.println("setNumIdAutoFor: " + cName);
    			}
    			if(nid == -1) {
    				nid = config.addEntryAuto(cName);
    				System.out.println("addEntryAuto: " + cName);
    			}
    			if(nid != -1 && nid != 0) {
    				if(nid < runeIdsv.length) {
	    				if(runeIdsv[nid] == null) {
	    					runeIdsv[nid]=cName;
	    				} else {
	    					CelestialWizardry.log.error("Attempted to overwrite rune: " + cName);
	    				}
    				}
    			}
    			if(nid == -1 || nid == 0) {
    				CelestialWizardry.log.error("Unable to register rune: " + cName);
    			}
    		}
        	configLoaded = true;
    	} else {
    		CelestialWizardry.log.error("Trying to setup numberic ids and runeIds isn't empty!"); // TODO throw exception
    	}
    }
    
    public static void registerRuneTextureLocations(String modid, ResourceLocation[] locs) {
    	if(!runeTexLocs.containsKey(modid)) {
    		runeTexLocs.put(modid, locs);
    	}
    }
    
    /**Called at world creation from *RuneConfigurationHandler to "set" the numeric ids without the config file.*/
    public static void onCreateConfig() {
    	Set<String> runeNames = runeMap.keySet();
    	if(!runeNames.isEmpty()) {
    		runeIdsv = new String[runeNames.size()+1];
    		Iterator<String> iter = runeNames.iterator();
    		int i=1;
    		while(iter.hasNext()) {
    			if(i >= runeIdsv.length) {
    				CelestialWizardry.log.error("Ran out of space in runeIdsv!");
    				break;
    			}
    			runeIdsv[i]=iter.next();
    			i++;
    		}
    		configLoaded = true;
    	} else {
    		CelestialWizardry.log.error("Can't set ids for no runes (no runes are registered)!"); // TODO throw exception
    	}
    }
    
    /**Are the numIds set up*/
    public static boolean isConfigLoaded() {
    	return configLoaded;
    }

    public static Rune getRuneByName(String name)
    {
    	if(runeMap.containsKey(name)) {
    		return runeMap.get(name);
    	}
    	
        CelestialWizardry.log.warn("Trying to get null rune, skipping!");

        return null;
    }
    
    public static Rune getRuneByNumId(int id) {
    	
    	if(!configLoaded) {
    		CelestialWizardry.log.warn("Trying to get rune by it's numberic id before config is loaded");
    	}
    	
    	if(id < runeIdsv.length && id > 0 && runeMap.containsKey(runeIdsv[id])) {
    		return runeMap.get(runeIdsv[id]);
    	}
    	
    	CelestialWizardry.log.warn("Trying to get null rune, skipping!");
    	
    	return null;
    }
    
    public static void reset() {
    	runeIdsv = null;
    	configLoaded = false;
    }
    
    /**Don't use this for iterating through the array! Use "getRuneIdsvLength()" instead. Only for visual purposes.*/
    public static int getRuneCount() {
    	return runeIdsv == null ? -1 : runeIdsv.length-1;
    }
    
    /**This should be used when iterating through the registered runes instead of getRuneCount()*/
    public static int getRuneIdsvLength() {
    	return runeIdsv == null ? -1 : runeIdsv.length;
    }
    
    public static String getRuneNameForId(int id) {
    	return runeIdsv == null ? null : id < runeIdsv.length ? runeIdsv[id] : null; 
    }
}
