package celestibytes.celestialwizardry.client.gui.spellbook.object;

import celestibytes.celestialwizardry.client.gui.spellbook.PageRenderer;

public abstract class PageObject {
	
	public final PageObjectType type;
	
	public PageObject(PageObjectType type) {
		this.type = type;
	}
	
	public abstract void render(PageRenderer pr);
	
	public enum PageObjectType {
		TEXT,
		IMAGE,
		RECIPE;
	}
}
