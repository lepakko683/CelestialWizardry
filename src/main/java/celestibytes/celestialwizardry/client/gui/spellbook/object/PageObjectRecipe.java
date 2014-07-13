package celestibytes.celestialwizardry.client.gui.spellbook.object;

import celestibytes.celestialwizardry.client.gui.spellbook.PageRenderer;

public class PageObjectRecipe extends PageObject {
	
	private int slotDefaultSize = 8;

	@Override
	public void render(PageRenderer pr) {
		
	}
	
	public enum RecipeType {
		CRAFTING2X2,
		CRAFTING3X3,
		SMELTING,
		BREWING;
	}

}
