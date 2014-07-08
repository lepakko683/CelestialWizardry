package celestialwizardry.registry;

import java.util.HashMap;
import java.util.Map;

import celestialwizardry.client.gui.spellbook.Page;
import celestialwizardry.util.LogH;

public class SpellBookPages {
	
	public static Map<String, Page> pages = new HashMap<String, Page>();
	
	public static void addPage(String id, Page page) {
		if(!pages.containsKey(id)) {
			pages.put(id, page);
			LogH.debug("Added page \"" + id + "\" to SpellBookPages.");
		} else {
			LogH.warn("Page \"" + id + "\" wasn't added to SpellBookPages");
		}
	}
}
