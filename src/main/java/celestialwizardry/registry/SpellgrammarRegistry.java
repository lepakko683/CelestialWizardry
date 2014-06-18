package celestialwizardry.registry;

import java.util.HashMap;
import java.util.Map;

import celestialwizardry.api.spellgrammar.SpellgrammarHandler;

public class SpellgrammarRegistry {
	
	private static Map<String, SpellgrammarHandler> handlers = new HashMap<String, SpellgrammarHandler>();
	
	
	public static void registerSpellgrammarHandler(SpellgrammarHandler h) {
		if(h.getModId() != null) {
			
		}
	}
}
