package celestialwizardry.handler;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.world.WorldEvent;

import celestialwizardry.CelestialWizardry;
import celestialwizardry.api.spellgrammar.Rune;
import celestialwizardry.registry.RuneRegistry;

public class GlobalRuneConfigurationHandler {
	
	//register runes
	//load config
	//check registered runes and loaded config
	//assign correct rune ids for runes
	
	public static boolean needToLoadConfig = true;
	
	//Load after runes have been registered
	public static void init() {
		//load config file
		Set<Entry<Integer, Rune>> runes = RuneRegistry.runeMap.entrySet();
		if(!runes.isEmpty()) {
			Iterator<Entry<Integer, Rune>> rite = runes.iterator();
			while(rite.hasNext()) {
				Entry<Integer, Rune> ent = rite.next();
			}
		} else {
			System.err.println("Something has went terribly wrong!!!"); //TODO: use logger
		}
	}
	
	@SubscribeEvent
	public void onWorldLoad(WorldEvent.Load event) {
		CelestialWizardry.log.info("WORLD LOAD EVENT!!!!!");
	}
	
	@SubscribeEvent
	public void onWorldSave(WorldEvent.Save event) {
        CelestialWizardry.log.info("WORLD SAVE EVENT!!!!!");
	}
	
	private static boolean configExistsAlready() {
		return false;
	}
	
	public static void onPlayerJoinOrLeave(int plrCount) {
		if(plrCount <= 0) { //It'd be a surprise to me if the player count actually went below 0
			needToLoadConfig = true;
		}
		if(needToLoadConfig && plrCount > 0) {
			needToLoadConfig = false;
			
			//TODO:load config
		}
	}
}
