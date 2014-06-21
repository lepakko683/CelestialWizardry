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

public abstract class RuneRegistry
{
    public static Map<String, Rune> runeMap = new HashMap<String, Rune>();
    public static List<String> runeIds = new ArrayList<String>();
    private static String[] runeIdsv = null;
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
    
//    /**Called from *RuneConfigurationHandler. Sets numeric ids for runes from config*/
//    public static void setupNumIds(ArrayList<String> config) {
//    	if(!configLoaded && runeIds.isEmpty()) {
//    		for(int i=0;i<config.size();i++) {
//        		runeIds.add(i, config.get(i));
//        	}
//        	configLoaded = true;
//    	} else {
//    		CelestialWizardry.log.error("Trying to setup numberic ids and runeIds isn't empty!"); // TODO throw exception
//    	}
//    }
    
    public static void setupNumIds(RuneConfig config) {
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
    	if(!configLoaded && runeIds.isEmpty()) {
    		Iterator<String> rNames = runeMap.keySet().iterator();
    		String cName = null;
    		int nid = -1;
    		while (rNames.hasNext()) {
    			cName = rNames.next();
    			nid = config.getNumId(cName);
    			if(nid == -1) {
    				nid = config.addEntryAuto(cName);
    			}
    			if(nid != -1) {
    				runeIdsv[nid]=cName;
    			} else {
    				CelestialWizardry.log.error("Unable to register rune: " + cName);
    			}
    		}
        	configLoaded = true;
    	} else {
    		CelestialWizardry.log.error("Trying to setup numberic ids and runeIds isn't empty!"); // TODO throw exception
    	}
    }
    
    /**Called at world creation from *RuneConfigurationHandler to "set" the numeric ids without the config file.*/
    public static void onCreateConfig() {
    	Set<String> runeNames = runeMap.keySet();
    	if(!runeNames.isEmpty()) {
    		runeIdsv = new String[runeNames.size()];
    		Iterator<String> iter = runeNames.iterator();
    		int i=0;
    		while(iter.hasNext()) {
    			runeIdsv[i]=iter.next();
    		}
    		configLoaded = true;
    	} else {
    		CelestialWizardry.log.error("Can't set ids for no runes (no runes are registered)!"); // TODO throw exception
    	}
    }
    
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
    	
    	if(id < runeIds.size() && id >= 0 && runeMap.containsKey(runeIds.get(id))) {
    		return runeMap.get(runeIds.get(id));
    	}
    	
    	CelestialWizardry.log.warn("Trying to get null rune, skipping!");
    	
    	return null;
    }
    
    public static void reset() {
    	runeIdsv = null;
    }
}
