package celestialwizardry.client.gui.spellbook;

import celestialwizardry.client.render.RuneRenderer;
import net.minecraft.client.gui.FontRenderer;

public class PageRenderer {
	protected final FontRenderer fontRenderer;
	protected final RuneRenderer runeRenderer;
	
	protected int pageObjectIndex;
	
	private static PageRenderer INSTANCE;
	
	public static PageRenderer getInstance() {
		return INSTANCE;
	}
	
	public static void init() {
		
	}
	
	public PageRenderer(FontRenderer fontRe, RuneRenderer runeRe) {
		this.fontRenderer = fontRe;
		this.runeRenderer = runeRe;
	}
	
	public void renderPage(Page p) {
		int subPages = p.pageData.length;
	}
}
